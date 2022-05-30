package com.yeahbutstill.springdemos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Spring will scan for component that implements
 */
@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // define a default constructor
    public TennisCoach() {
        System.out.println(">> inside default constructor");
    }

    // constructor injection method
    // configure dependency injection with @Autowired annotation
//    @Autowired
//    public TennisCoach(FortuneService fortuneService) {
//        System.out.println("fortuneService " + fortuneService);
//        this.fortuneService = fortuneService;
//    }

    public FortuneService getFortuneService() {
        return fortuneService;
    }

    // define a setter injection method
    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println(">> TennisCoach: inside setFortuneService() method " + fortuneService);
        this.fortuneService = fortuneService;
    }

    // method injection
    @Autowired
    public void doSomeCrazyStuff(FortuneService fortuneService) {
        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method " + fortuneService);
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand dani";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
