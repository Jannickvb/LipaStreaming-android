package com.jannick.lipastreaming.model.jsonTokens;

import java.util.List;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamToken {

    private boolean succes;
    private String device;

    private Stream[] streams;

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

    public Stream[] getStreams() {
        return streams;
    }

    public void setStreams(Stream[] streams) {
        this.streams = streams;
    }

    public static class Stream{
        private int id;
        private String name;
        private String ip;
        private int port;
        private String path;

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

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
