package org.interview.Connected;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConnChecker {

    private Map<String, List<City>> connections;

    public ConnChecker() {
        this.connections = new TreeMap<String, List<City>>();
    }

    private void loadConnection(String from, String to) {
        if(this.connections.containsKey(from)) {
            this.connections.get(from).add(new City(to, false));
        } else {
            this.connections.put(from, new ArrayList<City>(
                    List.of(new City(to, false))
            ));
        }
    }

    private void reset() {
        this.connections.forEach((city, conn) -> {
            conn.forEach(c -> {
                c.setConnected(false);
            });
        });
    }

    private void markConn(String from) {
        for (City city : this.connections.get(from)) {
            if(city.isConnected() == false) {
                city.setConnected(true);
                markConn(city.getName());
            }
        }
    }

    public void loadBiConnection(String from, String to) {
        loadConnection(from, to);
        loadConnection(to, from);
    }

    public boolean check(String from, String to) {
        reset();

        // handle simple cases
        if(this.connections.containsKey(from) == false) return false;
        if(this.connections.containsKey(to) == false) return false;

        markConn(from);

        for(City city : this.connections.get(to)) {
            if(city.isConnected()) return true;
        }

        // no connection marked between city:from and city:to
        return false;
    }
}
