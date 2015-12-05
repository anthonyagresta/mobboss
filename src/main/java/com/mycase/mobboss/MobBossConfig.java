package com.mycase.mobboss;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MobBossConfig {
    public static String CONFIG_FILENAME = "~/.mobboss";
    public static int DEFAULT_TURN_LENGTH = 300;
    private ArrayList<MobParticipant> mobbers;
    private int turnLengthSeconds;

    public ArrayList<MobParticipant> getMobbers() {
        return mobbers;
    }

    public void setMobbers(ArrayList<MobParticipant> mobbers) {
        this.mobbers = mobbers;
    }

    public int getTurnLengthSeconds() {
        return turnLengthSeconds;
    }

    public void setTurnLengthSeconds(int turnLengthSeconds) {
        this.turnLengthSeconds = turnLengthSeconds;
    }

    public MobBossConfig() {
        // Init defaults
        mobbers = new ArrayList<MobParticipant>();
        turnLengthSeconds = DEFAULT_TURN_LENGTH;
    }

    public void saveToDefault() {
        saveToFile(CONFIG_FILENAME);
    }

    public MobBossConfig loadFromDefault() {
        return loadFromFile(CONFIG_FILENAME);
    }

    public void saveToFile(String filepath) {
        try {
            this.toProp().store(new FileWriter(filepath), "MobBoss config file");
        } catch (IOException e) {
            //weh.
        }
    }

    public static MobBossConfig loadFromFile(String filepath) {
        Properties temp = new Properties();
        try {
            temp.load(new FileReader(filepath));
        } catch (IOException e) {
            //weh
        }
        return fromProp(temp);
    }

    private Properties toProp() {
        Properties retval = new Properties();
        retval.setProperty("turnLengthSeconds", Integer.toString(turnLengthSeconds));
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(MobParticipant m: this.mobbers) {
            if(first) {
                first = false;
            } else {
                sb.append("|");
            }
            sb.append(m.getName());
        }
        retval.setProperty("mobbers", sb.toString());
        return retval;
    }

    private static MobBossConfig fromProp(Properties prop) {
        MobBossConfig retval = new MobBossConfig();
        ArrayList<MobParticipant> newlist = new ArrayList<MobParticipant>();
        for(String name: prop.getProperty("mobbers").split("|")) {
            newlist.add(new MobParticipant(name));
        }
        retval.setMobbers(newlist);
        retval.setTurnLengthSeconds(Integer.parseInt(prop.getProperty("turnLengthSeconds")));

        return retval;
    }
}
