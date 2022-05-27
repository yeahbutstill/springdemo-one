package com.yeahbutstill.springdemo;

public class TrackCoach implements Coach {

    private FortuneService fortuneService;

    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public TrackCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {
        return "Just Do it: " + fortuneService.getForTune();
    }

    // add an init method
    public void doMyStartUpStuff() {
        System.out.println("TrackCoach: inside method doMyStartUpStuff");
    }

    // add a destroy method
    public void doMyDestroyStuff() {
        System.out.println("TrackCoach: inside method doMyDestroyStuff");
    }
}
