package org.kata.tondeuse.model;

import java.util.*;

public class Pelouse {

    private final Map<String, Integer> surface;

    private static Pelouse instance;

    private Pelouse(Map<String, Integer> surface) {
        this.surface = surface;
    }

    public static Pelouse getInstance(){
        if(instance == null){
            instance = new Pelouse(new HashMap<>());
        }
        return instance;
    }

    public void setSurface(int maxX, int maxY) {
        surface.put("x", maxX);
        surface.put("y", maxY);
    }

    public Map<String, Integer> getSurface(){
        return surface;
    }
}
