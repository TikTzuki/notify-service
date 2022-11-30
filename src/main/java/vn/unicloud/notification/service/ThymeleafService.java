package vn.unicloud.notification.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Service
public class ThymeleafService {
    private static final TemplateEngine templateEngine;

    static {
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        return templateEngine;
    }


    public String getContent() {
        final Context context = new Context();

        context.setVariable("name", "Messi");
        context.setVariable("project_name", "spring-email-with-thymeleaf Demo");

        return templateEngine.process(
                "<!DOCTYPE html>\n" +
                        "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                        "<body>\n" +
                        "<p style=\"color: red; font-weight: bold\" th:text=\"'Dear ' + ${name} + ','\"></p>\n" +
                        "<p th:text=\"'This is ' + ${project_name} + ' project.'\"></p>\n" +
                        "<p>Thanks!</p>\n" +
                        "</body>\n" +
                        "</html>", context);
    }
}