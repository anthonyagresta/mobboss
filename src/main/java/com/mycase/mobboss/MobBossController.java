package com.mycase.mobboss;

import java.util.Properties;

public class MobBossController {
    public static MobBossConfig config;
    public static CountdownTimerController timerController;
    public static MobQueue queue;

    public MobBossController() {
        config = new MobBossConfig();
        queue = new MobQueue();
    }

    public void startTimer() {
        timerController = new CountdownTimerController(this, config.getTurnLengthMinutes() * 60);
        timerController.start();
    }

    public void ring() {
        System.out.println("**** RING RING! ****");
    }
}
