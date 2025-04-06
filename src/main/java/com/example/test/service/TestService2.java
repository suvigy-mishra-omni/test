package com.example.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestService2 {
  public TestService2() throws IOException {
    test();
  }

  public void test() throws IOException {

    TemplateEngine templateEngine = new SpringTemplateEngine();
    Context context = new Context();
//    Map<String, String> map = new HashMap<>();
//    map.put("loanAccountNumber", "test");
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("customerName", "Vyom Bajpeyee");
    dataMap.put("loanAccountNumber", "MCFL_101_000002E7");
    dataMap.put("cuidNo", "");
    dataMap.put("ckycNo", "12345678901234");
    dataMap.put("loanAmount", "1000000.00");
    dataMap.put("applicationType", "GOLD SUB-PRODUCT");
    dataMap.put("scheme", "MGL-7");
    dataMap.put("loanPurpose", "Gold Loan for Debt Clearance");
    dataMap.put("applicationDate", "03-04-2025");
    dataMap.put("sourceOfIncome", "Salaried");
    dataMap.put("natureOfOccupation", "no");
    dataMap.put("nameOfEmployer", "your truly");
    dataMap.put("typeOfActivity", "letter writing");
    dataMap.put("landType", "gmail");
    dataMap.put("landHolding", "10");
    dataMap.put("loanCategory", "Individual");
    dataMap.put("grossAnnualIncome", "20-25L");
    dataMap.put("borrowerPhoto", "");
    dataMap.put("applicantName", "");
    dataMap.put("maidenName", "");
    dataMap.put("gender", "Male");
    dataMap.put("dateOfBirth", "03-05-2010");
    dataMap.put("panNumber", "BWFPB4464B");
    dataMap.put("aadharNumber", "XXXX-XXXX-5731");
    dataMap.put("fatherName", "MS");
    dataMap.put("motherName", "Seema");
    dataMap.put("maritalStatus", "Married");
    dataMap.put("spouseName", "Ayushi");
    dataMap.put("nationality", "Indian");
    dataMap.put("religion", "hindu");
    dataMap.put("caste", "hindu");
    dataMap.put("qualification", "Btech");
    dataMap.put("email", "vyombajpeyee@gmail.com");
    dataMap.put("mobile", "8131047527");
    dataMap.put("idProof", "PAN");
    dataMap.put("addressProof", "Aadhaar");
    dataMap.put("currentAddress", "");
    dataMap.put("city", "");
    dataMap.put("pincode", "208010");
    dataMap.put("landmark", "jajmau");
    dataMap.put("state", "up");
    dataMap.put("residentialTel", "");
    dataMap.put("yearsAtCurrentResidence", "6");
    dataMap.put("permanentAddress", "");
    dataMap.put("permanentCity", "");
    dataMap.put("permanentPincode", "");
    dataMap.put("permanentLandmark", "");
    dataMap.put("permanentState", "");
    dataMap.put("permanentMobile", "");
    dataMap.put("srNo", "4");
    dataMap.put("particular", "ring");
    dataMap.put("units", "20");
    dataMap.put("carat", "24");
    dataMap.put("totalWeight", "600"); // Overwrites earlier "200"
    dataMap.put("grossWeight", "200");
    dataMap.put("value", "1800000.00");
    dataMap.put("goldPacketNo", "74db125c-8002-4678-9d10-87aada2832f1");
    dataMap.put("collateralPhoto", "");
    dataMap.put("loanStartDate", "");
    dataMap.put("principalAmount", "");
    dataMap.put("tenure", "72");
    dataMap.put("baseInterestRate", "12.00000");
    dataMap.put("penalCharge", "");
    dataMap.put("principalDueDate", "");
    dataMap.put("principalRepaymentFrequency", "4");
    dataMap.put("interestDueDate", "");
    dataMap.put("interestRepaymentFrequency", "");
    dataMap.put("modeOfComputation", "");
    dataMap.put("bankName", "ICICI");
    dataMap.put("branchName", "Koramangala");
    dataMap.put("ifscCode", "ICICI080908");
    dataMap.put("accountNumber", "2345678");
    dataMap.put("accountType", "Current");
    dataMap.put("borrowerName", "defaultBorrowerName");
    dataMap.put("rateOfInterest", "12.00000");
    dataMap.put("interestFrequency", "");
    dataMap.put("riskCategory", "");
    dataMap.put("dateToday", "05-04-2025");
    dataMap.put("customerId", "MCFL_101_000002E7");
    dataMap.put("goldPacketNumber", "");
    dataMap.put("loanAccountNo", "MCFL_101_000002E7");
    dataMap.put("loanDate", "");
    dataMap.put("totalValue", "");
    dataMap.put("nameOfTheValuer1", "Deepesh");
    dataMap.put("valuer1EmployeeCode", "");
    dataMap.put("valuer1Signature", "Deepesh");
    dataMap.put("nameOfTheValuer2", "Deepesh");
    dataMap.put("valuer2employeeCode", "");
    dataMap.put("valuer2signature", "Deepesh");
    dataMap.put("disbursalDate", "");
    dataMap.put("deductionDetails", "");
    dataMap.put("totalChargeAmount", "");
    dataMap.put("totalCollectedUpfront", "");
    dataMap.put("totalDeductedCharges", "");
    dataMap.put("inCash", "");
    dataMap.put("otherThanCash", "");
    dataMap.put("beneficiaryName", "");
    dataMap.put("disbursalAmountInWords", "2499400.00");
    dataMap.put("utrNo", "");
    dataMap.put("remarks", "");
    dataMap.put("paymentMethod", "");


    templateEngine.setTemplateResolver(new StringTemplateResolver());
//    templateEngine.addDialect(new SpringStandardDialect());
    String templateHtml = new String(Files.readAllBytes(Paths.get("src/main/resources/index1.txt")), StandardCharsets.UTF_8);
    String processedTemplate = templateEngine.process(templateHtml, context);

    System.out.println("done");
  }

}
