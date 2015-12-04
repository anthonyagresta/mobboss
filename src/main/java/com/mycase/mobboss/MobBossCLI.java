package com.mycase.mobboss;

public class MobBossCLI {
    public static void main(String[] args) {
        MobBossController controller = new MobBossController();
        controller.config.setTurnLengthMinutes(1);
        controller.startTimer();
        while(controller.timerController.getTimeLeft() > 0) {
            // wait forever!
        }
    }
}
