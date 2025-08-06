package com.example.test.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Service
public class TestService2 {
  public TestService2() throws IOException {
    String date = "2025-06-10T18:30:00.000Z";
    try {
      String[] patterns = {"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"};
      DateUtils.parseDate(date, patterns).getTime();

    } catch (Exception e) {
      System.out.println("error => " + e.getMessage());
    }
  }

  public void test() throws IOException {

    TemplateEngine templateEngine = new SpringTemplateEngine();
    Context context = new Context();
//    Map<String, String> map = new HashMap<>();
//    map.put("loanAccountNumber", "test");
    Map<String, Object> data = new HashMap<>();

    data.put("customerName", "Deepesh  test-suite");
    data.put("loanAccountNumber", "APMCFLGL10100000942");
    data.put("cuidNo", "5ec5df8a-b937-40a4-ab70-0c6f9e5dc096");
    data.put("ckycNo", "86768765465467");
    data.put("loanAmount", "1000.00");
    data.put("applicationType", "Fresh Loan");
    data.put("scheme", "MGL-7");
    data.put("loanPurpose", "Education Fees");
    data.put("applicationDate", "16-04-2025");
    data.put("sourceOfIncome", "Agriculture");
    data.put("natureOfOccupation", "");
    data.put("nameOfEmployer", "");
    data.put("typeOfActivity", "");
    data.put("landType", "Agricultural");
    data.put("landHolding", "123");
    data.put("loanCategory", "Individual");
    data.put("grossAnnualIncome", "234567");
    data.put("borrowerPhoto", null);
    data.put("applicantName", "Deepesh  test-suite");
    data.put("maidenName", "Odessa Curry");
    data.put("gender", "Male");
    data.put("dateOfBirth", "09-04-2007");
    data.put("panNumber", "QWEDS4565H");
    data.put("aadharNumber", "XXXX-XXXX-7113");
    data.put("fatherName", "Rafai");
    data.put("motherName", "masthan");
    data.put("maritalStatus", "Other");
    data.put("spouseName", "no");
    data.put("nationality", "Indian");
    data.put("religion", "hindu");
    data.put("caste", "hindu");
    data.put("qualification", "intermediate");
    data.put("email", "Rafi@gmail.com");
    data.put("mobile", "9889456789");
    data.put("idProof", "PAN");
    data.put("addressProof", "Aadhaar");
    data.put("currentAddress", "");
    data.put("city", "");
    data.put("pincode", "517325");
    data.put("landmark", "New bypass");
    data.put("state", "Andhra Pradesh");
    data.put("residentialTel", null);
    data.put("yearsAtCurrentResidence", "4");
    data.put("permanentAddress", "");
    data.put("permanentCity", "");
    data.put("permanentPincode", "");
    data.put("permanentLandmark", "");
    data.put("permanentState", "");
    data.put("permanentMobile", null);

    List<Map<String, Object>> pledgedArticles = new ArrayList<>();
    Map<String, Object> article1 = new HashMap<>();
    article1.put("srNo", "1");
    article1.put("particular", "Necklace");
    article1.put("units", "345");
    article1.put("carat", "21");
    article1.put("totalWeight", "345");
    article1.put("grossWeight", "345");
    article1.put("value", "8265886.05");
    article1.put("goldPacketNo", "83945972-f844-4825-a641-019b8daf1b9f");
    pledgedArticles.add(article1);

    Map<String, Object> article2 = new HashMap<>();
    article2.put("srNo", "2");
    article2.put("particular", "Bangles");
    article2.put("units", "123");
    article2.put("carat", "18");
    article2.put("totalWeight", "123");
    article2.put("grossWeight", "123");
    article2.put("value", "2525972.28");
    article2.put("goldPacketNo", "6e26226b-10a2-4128-b6b0-624e3d66a6f8");
    pledgedArticles.add(article2);

    data.put("pledgedArticles", pledgedArticles);

    data.put("totalWeight", "468");
    data.put("collateralPhoto", "https://eks-common-uat2-..."); // truncated for brevity
    data.put("loanStartDate", "");
    data.put("principalAmount", "1000.00");
    data.put("tenure", "3");
    data.put("baseInterestRate", "12.00000");
    data.put("penalCharge", "");
    data.put("principalDueDate", "");
    data.put("principalRepaymentFrequency", "Quarterly");
    data.put("interestDueDate", "");
    data.put("interestRepaymentFrequency", "Quarterly");
    data.put("modeOfComputation", "Fixed");
    data.put("bankName", "Harper Barr");
    data.put("branchName", "Hollee Dominguez");
    data.put("ifscCode", "1234567UYT");
    data.put("accountNumber", "1234566543");
    data.put("accountType", "Current");
    data.put("borrowerName", "Deepesh  test-suite");
    data.put("rateOfInterest", "12.00000");
    data.put("interestFrequency", "Fixed");
    data.put("riskCategory", "high");
    data.put("dateToday", "28-04-2025");
    data.put("customerId", "5ec5df8a-b937-40a4-ab70-0c6f9e5dc096");
    data.put("goldPacketNumber", "APMCFLGL10100000942");
    data.put("loanAccountNo", "APMCFLGL10100000942");
    data.put("loanDate", "");
    data.put("totalValue", "10791858.33");
    data.put("nameOfTheValuer1", "");
    data.put("valuer1EmployeeCode", "f958d050-c4bd-4209-b12d-679b0ca12bd5");
    data.put("valuer1Signature", "");
    data.put("nameOfTheValuer2", "");
    data.put("valuer2employeeCode", "36fc170d-ac8f-49f4-b427-021d10396e0b");
    data.put("valuer2signature", "");
    data.put("valuationPhoto", null);
    data.put("disbursalDate", "");

    List<Map<String, Object>> deductionDetails = new ArrayList<>();
    String[][] deductions = {
            {"1", "Processing Fees"},
            {"2", "Insurance Amount"},
            {"3", "Documentation Charges"},
            {"4", "Credit Report Charges"},
            {"5", "Valuation Charges"},
            {"6", "Franking Charges"}
    };
    for (String[] deduction : deductions) {
      Map<String, Object> entry = new HashMap<>();
      entry.put("srNo", deduction[0]);
      entry.put("natureOfCharges", deduction[1]);
      entry.put("charges", "Rupees");
      entry.put("chargeAmount", "0");
      entry.put("collectedUpfront", "true");
      entry.put("deductedCharges", "true");
      deductionDetails.add(entry);
    }
    data.put("deductionDetails", deductionDetails);

    data.put("totalChargeAmount", "0.00");
    data.put("totalCollectedUpfront", "");
    data.put("totalDeductedCharges", "");
    data.put("inCash", "");
    data.put("otherThanCash", "");
    data.put("beneficiaryName", "");
    data.put("disbursalAmountInWords", "1000.00");
    data.put("utrNo", "");
    data.put("remarks", "");
    data.put("paymentMethod", "");


    templateEngine.setTemplateResolver(new StringTemplateResolver());
    context.setVariables(Collections.unmodifiableMap(data));
//    templateEngine.addDialect(new SpringStandardDialect());
    String templateHtml = new String(Files.readAllBytes(Paths.get("src/main/resources/index2.txt")), StandardCharsets.UTF_8);
    String processedTemplate = templateEngine.process(templateHtml, context);

    System.out.println("done");
  }

  public void test2() throws IOException {

    Map<String, Object> data = new HashMap<>();

    List<Map<String, String>> borrowerDetails = Arrays.asList(
            createEntry("Customer Name", "Vyom Bajpeyee"),
            createEntry("Loan Account Number", "APMCFLGL10100000896"),
            createEntry("Gender", "Male"),
            createEntry("Date of Birth", "24-06-1995"),
            createEntry("PAN Number", "BWFPB4464B"),
            createEntry("Aadhar Number", "XXXX-XXXX-5731"),
            createEntry("Mobile Number", "8131047527")
    );

    List<Map<String, String>> collateralDetails = Arrays.asList(
            createEntry("Asset Type", "Physical"),
            createEntry("Bin Number", "1342")
    );

    List<Map<String, String>> valuationDetails = Arrays.asList(
            createEntry("Remarks", "checked again"),
            createEntry("Employee ID", "0895db48-37c5-491b-b5a2-c05183bf76ad"),
            createEntry("Name of the Valuer", null),
            createEntry("Rate per gram (in INR)", ""),
            createEntry("Units (in nos.)", "10"),
            createEntry("Total Weight (in gms)", "100"),
            createEntry("Deductions (in gms)", "10"),
            createEntry("Net Weight (in gms)", "90"),
            createEntry("Value (in INR)", "2070000.00")
    );

    List<Map<String, String>> creditDecisioningDetails = Arrays.asList(
            createEntry("Sanctioned Loan Amount", "1000000.00"),
            createEntry("Loan Tenure (in months)", "com.saison.omni.appform.entity.LoanTenure@142fc5b9"),
            createEntry("No. of Instalments", "4"),
            createEntry("Type of Instalment", "Quarterly"),
            createEntry("Interest Rate Type", "Fixed"),
            createEntry("Rate of Interest (in %)", "20.00000"),
            createEntry("Processing Fees (in INR)", "0.00"),
            createEntry("Insurance Premium (in INR)", "0.00"),
            createEntry("Documentation Charges (in INR)", "0"),
            createEntry("Credit Report Charges", "0"),
            createEntry("Valuation Charges", "0"),
            createEntry("Franking charges", "0"),
            createEntry("Net Loan Amount", "1000000.00")
    );

    data.put("borrowerDetails", borrowerDetails);
    data.put("collateralDetails", collateralDetails);
    data.put("valuationDetails", valuationDetails);
    data.put("creditDecisioningDetails", creditDecisioningDetails);

    TemplateEngine templateEngine = new SpringTemplateEngine();
    Context context = new Context();
    templateEngine.setTemplateResolver(new StringTemplateResolver());
    context.setVariables(Collections.unmodifiableMap(data));
    String templateHtml = new String(Files.readAllBytes(Paths.get("src/main/resources/index2.txt")), StandardCharsets.UTF_8);
    String processedTemplate = templateEngine.process(templateHtml, context);
    System.out.println("done");

  }
  private static Map<String, String> createEntry(String label, String value) {
    Map<String, String> map = new HashMap<>();
    map.put("label", label);
    map.put("value", value); // null-safe
    return map;
  }

}
