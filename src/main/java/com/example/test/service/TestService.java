package com.example.test.service;

import com.example.test.entity.Appform;
import com.example.test.entity.Borrower;
import com.example.test.entity.Difference;
import com.example.test.entity.LoanTerm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
  private final ObjectMapper mapper = new ObjectMapper();

  TestService() {
    test();
  }

  public void test() {
    Appform originalAppform =
        Appform.builder()
            .id("appform-1")
            .loanApplicationId("loan-app-1")
            .poolId("pool-id-1")
            .loanTerm(
                LoanTerm.builder().id("loan-term-1").interestRate(4).tenure("6 Months").build())
            .borrowers(
                List.of(
                    Borrower.builder().id("borrower-1").name("borrower-name-1").age(40).build(),
                    Borrower.builder().id("borrower-2").name("borrower-name-2").age(45).build()))
            .build();

    Appform updatedAppform = null;

    try {
      String copiedAppform = mapper.writeValueAsString(originalAppform);

      updatedAppform = mapper.readValue(copiedAppform, Appform.class);
    } catch (Exception e) {
      log.error("Galat ho gaya kuch {}", Arrays.toString(e.getStackTrace()));
    }

    updatedAppform.setPoolId(null);
    updatedAppform.setMasterPoolId("master-pool-id-2");
    updatedAppform.getLoanTerm().setTenure("8 Months");
    updatedAppform.getBorrowers().get(0).setName("update-borrower-name-1");
    updatedAppform.getBorrowers().get(1).setId("borrower-3");
    updatedAppform.getBorrowers().get(1).setAge(35);

    try {
      LinkedHashMap<String, Object> original =
          mapper.convertValue(originalAppform, LinkedHashMap.class);
      LinkedHashMap<String, Object> updated =
          mapper.convertValue(updatedAppform, LinkedHashMap.class);

      var diff = diffFinder(original, updated, "appform");

      System.out.println(diff);
    } catch (Exception e) {
      log.error("Phataaaa");
    }
  }

  private List<Difference> diffFinder(
      LinkedHashMap<String, Object> original,
      LinkedHashMap<String, Object> updated,
      String prefix) {
    List<Difference> diff = new ArrayList<>();

    Set<String> keys = new HashSet<>();
    keys.addAll(original.keySet());
    keys.addAll(updated.keySet());

    for (String key : keys) {
      String fullPath = String.join(".", prefix, key);

      if (Objects.isNull(original.get(key)) && Objects.isNull(updated.get(key))) {
        continue;
      }

      if (Objects.isNull(original.get(key))) {
        diff.add(
            Difference.builder()
                .key(key)
                .fullPath(fullPath)
                .changeType("ADDITION")
                .originalValue(null)
                .updatedValue(updated.get(key))
                .build());

        continue;
      }

      if (Objects.isNull(updated.get(key))) {
        diff.add(
            Difference.builder()
                .key(key)
                .fullPath(fullPath)
                .changeType("REMOVAL")
                .originalValue(original.get(key))
                .updatedValue(null)
                .build());

        continue;
      }

      if (original.get(key) instanceof Map || updated.get(key) instanceof Map) {
        diff.addAll(
            diffFinder(
                mapper.convertValue(original.get(key), LinkedHashMap.class),
                mapper.convertValue(updated.get(key), LinkedHashMap.class),
                fullPath));

        continue;
      }

      if (original.get(key) instanceof List originalList
          && updated.get(key) instanceof List updatedList) {
        diff.addAll(findListDiff(originalList, updatedList, key, fullPath));

        continue;
      }

      if (!original.get(key).equals(updated.get(key))) {
        diff.add(
            Difference.builder()
                .key(key)
                .fullPath(fullPath)
                .changeType("UPDATE")
                .originalValue(original.get(key).toString())
                .updatedValue(updated.get(key).toString())
                .build());
      }
    }

    return diff;
  }

  private List<Difference> findListDiff(
      List<LinkedHashMap> originalList,
      List<LinkedHashMap> updatedList,
      String key,
      String prefix) {
    List<Difference> diff = new ArrayList<>();

    originalList.sort(
        (obj1, obj2) ->
            this.comparator(
                mapper.convertValue(obj1, LinkedHashMap.class),
                mapper.convertValue(obj2, LinkedHashMap.class)));

    updatedList.sort(
        (obj1, obj2) ->
            this.comparator(
                mapper.convertValue(obj1, LinkedHashMap.class),
                mapper.convertValue(obj2, LinkedHashMap.class)));

    int leftIndex = 0, rightIndex = 0;
    while (leftIndex < originalList.size()) {
      if (rightIndex < updatedList.size()
          && originalList.get(leftIndex).get("id").equals(updatedList.get(rightIndex).get("id"))) {
        diff.addAll(
            diffFinder(originalList.get(leftIndex++), updatedList.get(rightIndex++), prefix));
      } else {
        diff.add(
            Difference.builder()
                .key(key)
                .fullPath(String.join(".", prefix, key))
                .changeType("REMOVAL")
                .originalValue(originalList.get(leftIndex++))
                .updatedValue(null)
                .build());
      }
    }

    while (rightIndex < updatedList.size()) {
      diff.add(
          Difference.builder()
              .key(key)
              .fullPath(String.join(".", prefix, key))
              .changeType("ADDITION")
              .originalValue(null)
              .updatedValue(updatedList.get(rightIndex++))
              .build());
    }

    return diff;
  }

  private int comparator(LinkedHashMap obj1, LinkedHashMap obj2) {
    return ((String) obj1.get("id")).compareTo((String) obj2.get("id"));
  }
}
