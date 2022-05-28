package com.yeahbutstill.springdemos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Spring will scan for component that implements
 */
@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // configure dependency injection with @Autowired annotation
    @Autowired
    public TennisCoach(FortuneService fortuneService) {
        System.out.println("fortuneService " + fortuneService);
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
