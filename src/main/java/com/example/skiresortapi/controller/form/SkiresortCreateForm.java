package com.example.skiresortapi.controller.form;

import org.jetbrains.annotations.NotNull;

public class SkiresortCreateForm {
    @NotNull
    private String name;
    @NotNull
    private String area;
    @NotNull
    private String impression;

    public SkiresortCreateForm(String name, String area, String impression) {
        this.name = name;
        this.area = area;
        this.impression = impression;
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
        return this.impression;
    }

    public void setImpression(String impression) {

        this.impression = impression;
    }
}
