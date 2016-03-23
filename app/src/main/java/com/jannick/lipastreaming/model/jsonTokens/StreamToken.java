package com.jannick.lipastreaming.model.jsonTokens;

import java.util.List;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamToken {

    private Stream[] streams;

    public Stream[] getStreams() {
        return streams;
    }

    public void setStreams(Stream[] streams) {
        this.streams = streams;
    }

    public static class Stream {
        private String name;
        private String ip;
        private String port;
        private String path;

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

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
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
