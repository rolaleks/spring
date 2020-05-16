package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        //ApplicationContext context = new ClassPathXmlApplicationContext("config-scan.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
        context.getBean(CloudServer.class);
    }
}
