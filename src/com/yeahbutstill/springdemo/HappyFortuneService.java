package com.yeahbutstill.springdemo;

public class HappyFortuneService implements FortuneService {
    @Override
    public String getForTune() {
        return "Today is your lucky day!";
    }
}
