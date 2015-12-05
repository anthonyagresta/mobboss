package com.mycase.mobboss;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class CountdownTimerController {
    private MobBossController controller;
    private int initialSeconds;
    private int timeLeft;
    private CountdownTimer timer;
    private CountDownLatch latch;

    public CountdownTimerController(MobBossController mbc, int initialSeconds) {
        controller = mbc;
        this.initialSeconds = initialSeconds;
        timeLeft = initialSeconds;
        latch = new CountDownLatch(initialSeconds);
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
        latch = new CountDownLatch(initialSeconds);
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
        latch = new CountDownLatch(timeLeft);
        timer.start();
    }

    public void ring() {
        timeLeft = initialSeconds;
        timer.cancel();
        controller.ring();
    }

    public void tick() {
        timeLeft--;
        latch.countDown();
        System.out.printf("\rTick! %s left...   ", getTimeString());
        if (timeLeft <= 0) {
            ring();
        }
    }

    public String getTimeString() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft;
        if(minutes > 0) {
            seconds -= minutes * 60;
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void await() throws InterruptedException {
        latch.await();
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

