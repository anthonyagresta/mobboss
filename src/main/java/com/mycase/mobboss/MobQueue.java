package com.mycase.mobboss;

import java.util.LinkedList;
import java.util.Random;

public class MobQueue {
    private LinkedList<MobParticipant> theQueue;

    public MobQueue() {
        theQueue = new LinkedList<MobParticipant>();
    }

    public LinkedList<MobParticipant> getTheQueue() {
        return theQueue;
    }

    public int addToQueue(MobParticipant m) {
        theQueue.push(m);
        return theQueue.size();
    }

    public void shuffle() {
        LinkedList<MobParticipant> newQ = new LinkedList<MobParticipant>();
        synchronized(theQueue) {
            Random r = new Random();
            while (!theQueue.isEmpty()) {
                int index = r.nextInt(theQueue.size());
                newQ.push(theQueue.remove(index));
            }
        }
        theQueue = newQ;
    }

    public MobParticipant removeFromQueue(int index) {
        return theQueue.remove(index);
    }

    public MobParticipant getDriver() {
        return theQueue.peek();
    }

    public void advance() {
        theQueue.add(theQueue.pop());
    }

}
