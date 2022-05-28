package com.yeahbutstill.springdemos;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand dani";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
