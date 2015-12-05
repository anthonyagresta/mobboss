package com.mycase.mobboss;

import javafx.scene.media.AudioClip;

import java.net.URL;

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
        plonkNoise();
        System.out.println("\n**** RING RING! ****");
        System.out.flush();
    }

    public void plonkNoise() {
        URL u = getClass().getResource("/sounds/bleep.wav");
        AudioClip plonkNoise = new AudioClip(u.toString());
        plonkNoise.play();
    }
}
