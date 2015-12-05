package com.mycase.mobboss;

import java.util.Scanner;

public class MobBossCLI {
    private static MobBossController controller;
    private static Scanner console;

    public static void main(String[] args) {
        controller = new MobBossController();
        controller.config.setTurnLengthSeconds(10);
        console = new Scanner(System.in);

        System.out.println("MobBoss Timer!\n");
        askForTurnLength();

        int howMany = askForHowMany();
        while(controller.queue.size() < howMany) {
            addAMobber();
        }
        System.out.println("Starting Mobbing!");
        int times = controller.queue.size() + 3;
        do {
            controller.startTimer();
            synchronized(MobBossController.timerController) {
                System.out.println("\nCurrent queue: ");
                System.out.println(controller.queue.toString());
                System.out.printf("It is currently %s's turn!\n", controller.queue.getDriver().getName());
                System.out.printf("Next up is %s !\n", controller.queue.getOnDeck().getName());
                try {
                    MobBossController.timerController.await();
                } catch (InterruptedException e) {
                    // meh.
                }
            }
            controller.queue.advance();
            times--;
        } while(times > 0);
    }

    public static void addAMobber() {
        System.out.print("Enter Mobber Name> ");
        String mobberName = console.next();
        controller.queue.addToQueue(new MobParticipant(mobberName));
    }

    public static void askForTurnLength() {
        System.out.print("Enter turn length in seconds>");
        int turnLength = console.nextInt();
        controller.config.setTurnLengthSeconds(turnLength);
    }

    public static int askForHowMany() {
        System.out.print("Enter number of mobbers>");
        return console.nextInt();
    }
}
