package com.mycase.mobboss;

public class MobBossController {
    public static MobBossConfig config;
    public static CountdownTimerController timerController;
    public static MobQueue queue;

    public MobBossController() {
        config = new MobBossConfig();
        // TODO: load/save config
        queue = new MobQueue();
    }

    public void startTimer() {
        timerController = new CountdownTimerController(this, config.getTurnLengthSeconds());
        timerController.start();
    }

    public void ring() {
        System.out.println("\n**** RING RING! ****");
        System.out.flush();
    }
}
