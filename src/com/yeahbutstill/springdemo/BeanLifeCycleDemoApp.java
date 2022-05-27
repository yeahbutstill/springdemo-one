package com.yeahbutstill.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {
    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/yeahbutstill/springdemo/beanLifeCycle-applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);
        Coach alphaCoach = context.getBean("myCoach", Coach.class);

        // check if they are the same
        boolean resultSingleton = (theCoach.equals(alphaCoach));
        boolean resultPrototype = (!theCoach.equals(alphaCoach));
        System.out.println("\nPointing to the same object: " + resultSingleton);
        System.out.println("\nPointing to the not same object: " + resultPrototype);

        System.out.println("\nMemory location for theCoach: " + theCoach);
        System.out.println("\nMemory location for alphaCoach: " + alphaCoach + "\n ");

        System.out.println(theCoach.getDailyWorkout());

        // close the context
        context.close();

    }
}
