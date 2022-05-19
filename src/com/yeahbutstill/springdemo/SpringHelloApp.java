package com.yeahbutstill.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {
    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/yeahbutstill/springdemo/applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);

        // call method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // let's call our new method for fortunes
        System.out.println(theCoach.getDailyFortune());

        // close the context
        context.close();

    }
}
