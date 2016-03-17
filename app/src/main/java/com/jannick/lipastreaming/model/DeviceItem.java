package com.jannick.lipastreaming.model;

/**
 * Created by Jannick on 17-3-2016.
 */
public class DeviceItem {

    private String name,id,location,desc;
    private boolean currentDevice = false;

    public DeviceItem(String name, String id, String location, String desc){
        this.name = name;
        this.id = id;
        this.location = location;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(boolean currentDevice) {
        this.currentDevice = currentDevice;
    }
}
