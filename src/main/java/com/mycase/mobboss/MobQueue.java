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
        theQueue.add(m);
        return theQueue.size();
    }

    public int size() {
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

    public MobParticipant getOnDeck() {
        if(theQueue.size() > 1) {
            return theQueue.get(1);
        } else if( theQueue.size() == 1) {
            return theQueue.peek();
        } else {
            return null;
        }
    }

    public void advance() {
        theQueue.add(theQueue.pop());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for(MobParticipant mob: theQueue) {
            if(first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(mob.getName());
            sb.append("'");
        }
        sb.append("]");
        return sb.toString();
    }

}
