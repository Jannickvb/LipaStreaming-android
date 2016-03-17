package com.jannick.lipastreaming.model;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamItem {

    private String name,ip,port,path;

    public StreamItem(String name, String ip, String port, String path){
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.path = path;
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
