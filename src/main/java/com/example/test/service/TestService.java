package com.example.test.service;

import com.example.test.entity.Applicant;
import com.example.test.entity.Template;
import com.example.test.entity.TemplatePlaceholder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Service
public class TestService {
  private final TemplateEngine templateEngine;

  TestService(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;

    Map<String, Object> init = init();
    Map<String, Object> dataSource = dataSource();
  }

  private Map<String, Object> init() {
    Template template =
        Template.builder()
            .id("template-id-1")
            .templateKey("template-1")
            .html(
                """
                        <!DOCTYPE html>
                          <html lang="en">

                            <head>
                                <meta charset="UTF-8"/>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                                <title>Document</title>
                            </head>

                            <body>
                              <p>
                                 Borrower Name : <span th:text="${appform.name}">Not provided</span>
                              </p>

                              <p>
                                 Borrower City : <span th:text="${city}">Not provided</span>
                              </p>

                              <img height="100px" width="100px" src="" th:src="${imgUrl}"/>

                              <table>
                                  <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Age</th>
                                        <th>City</th>
                                    </tr>
                                  </thead>

                                  <tbody>
                                    <tr th:each="applicant: ${applicants}">
                                        <th th:text=${applicant.name}></th>
                                        <th th:text=${applicant.age}></th>
                                        <th th:text=${applicant.city}></th>
                                    </tr>
                                  </tbody>
                              </table>
                            </body>
                          </html>
             """)
            .build();

    TemplatePlaceholder namePlaceholder =
        TemplatePlaceholder.builder()
            .templateId(template.getId())
            .placeholder("name")
            .dataSource("name")
            .defaultValue("Name not available")
            .build();

    TemplatePlaceholder cityPlaceholder =
        TemplatePlaceholder.builder()
            .templateId(template.getId())
            .placeholder("city")
            .dataSource("city")
            .defaultValue("City not available")
            .build();

    TemplatePlaceholder imgUrl =
        TemplatePlaceholder.builder()
            .templateId(template.getId())
            .placeholder("imgUrl")
            .dataSource("profilePicture")
            .defaultValue("Not Provided")
            .build();

    TemplatePlaceholder applicantDetails =
        TemplatePlaceholder.builder()
            .templateId(template.getId())
            .placeholder("applicants")
            .dataSource("applicants")
            .defaultValue(null)
            .build();

    return new HashMap<>() {
      {
        put("template", template);
        put(
            "placeholders",
            new ArrayList<>() {
              {
                addAll(List.of(namePlaceholder, cityPlaceholder, imgUrl, applicantDetails));
              }
            });
      }
    };
  }

  private Map<String, Object> dataSource() {
    Map<String, Object> dataSource = new HashMap<>();

    dataSource.put("name", "Test Borrower");
    dataSource.put("city", "Bangalore");
    dataSource.put(
        "profilePicture",
        "https://scontent.fmaa5-1.fna.fbcdn.net/v/t39.30808-6/311475920_133294419460918_6050973295536649042_n.png?_nc_cat=111&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=AKzv6jqOTGkQ7kNvgFtyDxA&_nc_oc=AdgY7vQyiX-ltdRCMqsxhVRS3wA8HNcpcysNaQ_xQtr5LqdUxLxrStahywYfW1g24NE&_nc_zt=23&_nc_ht=scontent.fmaa5-1.fna&_nc_gid=A-iLkJZlLsNh9vVmdhCZnsh&oh=00_AYC0mtbTWF_4nW8XKApGVlixoJQ6oJxKBl31WUXD6WQ-fw&oe=67CDE214");

    dataSource.put(
        "applicants",
        List.of(
            Applicant.builder().age("40").name("applicant-1").city("delhi").build(),
            Applicant.builder().age("42").name("applicant-2").city("bangalore").build(),
            Applicant.builder().age("44").name("applicant-3").city("pune").build(),
            Applicant.builder().age("42").name("applicant-4").city("chennai").build()));

    return dataSource;
  }

  public void test(
      Template template, List<TemplatePlaceholder> placeholders, Map<String, Object> dataSource) {

    Context context = new Context();

    for (TemplatePlaceholder placeholder : placeholders) {
      String key = placeholder.getDataSource();

      context.setVariable(
          placeholder.getPlaceholder(),
          dataSource.getOrDefault(key, placeholder.getDefaultValue()));
    }

    templateEngine.setTemplateResolver(new StringTemplateResolver());

    String processedTemplate = templateEngine.process(template.getHtml(), context);

    System.out.printf("Processed template => " + processedTemplate);
  }
}
