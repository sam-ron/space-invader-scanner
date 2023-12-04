package com.ronan.space.invaders.model;

public abstract class RadarObject {

    private final String[][] data;

    public RadarObject(String[][] data) {
        this.data = data;
    }

    public String[][] getData() {
        return data;
    }

    public int getRows() {
        return data.length;
    }

    public int getCols() {
        return data[0].length;
    }
}