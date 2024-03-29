package com.example.skiresortapi.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SkiresortCreateForm {

    @Size(min = 1, max = 20)
    @NotBlank
    private String name;

    @Size(min = 1, max = 20)
    @NotBlank
    private String area;
    
    @Size(min = 1, max = 50)
    @NotBlank
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
