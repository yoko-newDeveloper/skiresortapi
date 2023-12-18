package com.example.skiresortapi.entity;

public class Skiresort {

    private int id;
    private String name;
    private String area;
    private String impression;

    public Skiresort(int id, String name, String area, String impression) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.impression = impression;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }
}
