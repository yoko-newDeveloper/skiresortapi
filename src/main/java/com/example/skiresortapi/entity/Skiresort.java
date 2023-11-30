package com.example.skiresortapi.entity;

public class Skiresort {

    private final int id;
    private final String name;
    private final String area;
    private final String impression;

    public Skiresort(int id, String name, String area, String impression) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.impression = impression;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getImpression() {
        return impression;
    }
}
