package com.jannick.lipastreaming.model.jsonTokens;

import java.util.List;

/**
 * Created by Jannick on 17-3-2016.
 */
public class AlarmToken {

    private boolean succes;
    private String device;

    private Alarm[] alarms;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Alarm[] getAlarms() {
        return alarms;
    }

    public void setAlarms(Alarm[] alarms) {
        this.alarms = alarms;
    }

    public static class Alarm {
        private int id;
        private String name;
        private String time;
        private String stream;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStream() {
            return stream;
        }

        public void setStream(String stream) {
            this.stream = stream;
        }
    }
}
