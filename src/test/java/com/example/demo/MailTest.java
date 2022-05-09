package com.example.demo;

import com.example.demo.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class )
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextEmail() {
        mailClient.sendMail("paradoxxxxxxx@sina.com", "ideaMail", "hello,success send email");
    }

    @Test
    public void textHtmlEmail(){
        Context context = new Context();
        context.setVariable("username", "ParaDox");
        System.out.println(templateEngine.process("/mail/demo",context));
        mailClient.sendMail("paradoxxxxxxx@sina.com", "ideaMail", templateEngine.process("/mail/demo",context));
    }

}
