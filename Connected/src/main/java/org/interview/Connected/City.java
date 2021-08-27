package org.interview.Connected;


public class City {
    private String name;
    private boolean connected;

    public City(String name, boolean isConnected) {
        this.name = name;
        this.connected = isConnected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
