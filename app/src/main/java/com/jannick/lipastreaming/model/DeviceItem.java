package com.jannick.lipastreaming.model;

/**
 * Created by Jannick on 17-3-2016.
 */
public class DeviceItem {

    private String device,name,description,location;
    private boolean currentDevice = false;

    public DeviceItem(String device, String name, String description, String location) {
        this.device = device;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(boolean currentDevice) {
        this.currentDevice = currentDevice;
    }
}
