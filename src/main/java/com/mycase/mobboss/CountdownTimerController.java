package com.mycase.mobboss;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimerController {
    private MobBossController controller;
    private int initialSeconds;
    private int timeLeft;
    private CountdownTimer timer;


    public CountdownTimerController(MobBossController mbc, int initialSeconds) {
        controller = mbc;
        this.initialSeconds = initialSeconds;
        timeLeft = initialSeconds;
    }
    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    public void setInitialSeconds(int s) {
        initialSeconds = s;
    }

    public void start() {
        timer = new CountdownTimer(this, initialSeconds);
        timer.start();
    }

    public void pause() {
        timer.cancel();
    }

    public void resume() {
        if(timeLeft <= 0) {
            timeLeft = initialSeconds;
        }
        timer = new CountdownTimer(this, timeLeft);
        timer.start();
    }

    public void ring() {
        timeLeft = initialSeconds;
        timer.cancel();
        controller.ring();
    }

    public void tick() {
        timeLeft--;
        System.out.printf("Tick! %d seconds left...\n", timeLeft);
        if (timeLeft <= 0) {
            ring();
        }
    }

    public String getTimeString() {
        // return timeLeft as a friendly mm:ss time string
        return "";
    }

    private class CountdownTimer extends Timer {
        private CountdownTimerController controller;
        private int secondsLeft;

        public CountdownTimer(CountdownTimerController controller, int initialSeconds) {
            this.controller = controller;
            secondsLeft = initialSeconds;
        }

        public void start() {
            this.scheduleAtFixedRate(new TickTask(this), 1000, 1000);
        }

        private void tick() {
            controller.tick();
        }

        private class TickTask extends TimerTask {
            public TickTask(CountdownTimer timer) {
            }

            @Override
            public void run() {
                timer.tick();
            }
        }
    }


}

