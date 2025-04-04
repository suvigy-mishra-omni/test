package com.example.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService2 {
  public TestService2() {
    test();
  }

  public static String templateHtml =
      """
         <!DOCTYPE html>
         <html lang="en">
           <head>
             <meta charset="UTF-8" />
             <meta name="viewport" content="width=device-width, initial-scale=1.0" />
             <title>Document</title>
           </head>
         
           <body>
              <span>[[${loanAccountNumber}]]</span>
              <span>[[${loanAccountNumber}]]</span>
             <div>This is a test template</div>
           </body>
         </html>
           """;

  public void test() {

    TemplateEngine templateEngine = new SpringTemplateEngine();
    Context context = new Context();
    Map<String, String> map = new HashMap<>();
    map.put("loanAccountNumber", "test");


    templateEngine.setTemplateResolver(new StringTemplateResolver());
    templateEngine.addDialect(new SpringStandardDialect());
    String processedTemplate = templateEngine.process(templateHtml, context);

    System.out.println("done");
  }
}
