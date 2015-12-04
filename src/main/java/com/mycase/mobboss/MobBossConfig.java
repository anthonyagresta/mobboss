package com.mycase.mobboss;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MobBossConfig {
    public static String CONFIG_FILENAME = "~/.mobboss";
    private ArrayList<MobParticipant> mobbers;
    private int turnLengthMinutes;

    public ArrayList<MobParticipant> getMobbers() {
        return mobbers;
    }

    public void setMobbers(ArrayList<MobParticipant> mobbers) {
        this.mobbers = mobbers;
    }

    public int getTurnLengthMinutes() {
        return turnLengthMinutes;
    }

    public void setTurnLengthMinutes(int turnLengthMinutes) {
        this.turnLengthMinutes = turnLengthMinutes;
    }

    public MobBossConfig() {
        // Init defaults
        mobbers = new ArrayList<MobParticipant>();
        turnLengthMinutes = 5;
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
        retval.setProperty("turnLengthMinutes", Integer.toString(turnLengthMinutes));
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
        retval.setTurnLengthMinutes(Integer.parseInt(prop.getProperty("turnLengthMinutes")));

        return retval;
    }
}
