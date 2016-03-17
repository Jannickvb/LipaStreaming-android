package com.jannick.lipastreaming.model;

/**
 * Created by Jannick on 17-3-2016.
 */
public class StreamItem {

    private String name,ip;

    public StreamItem(String name, String ip){
        this.name = name;
        this.ip = ip;
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
}
