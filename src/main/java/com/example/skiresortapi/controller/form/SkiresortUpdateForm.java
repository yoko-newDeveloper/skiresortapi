package com.example.skiresortapi.controller.form;

import jakarta.validation.constraints.NotBlank;

public class SkiresortUpdateForm {

    @NotBlank
    private final String name;
    @NotBlank
    private String area;
    @NotBlank
    private String impression;

    public SkiresortUpdateForm(String name, String area, String impression) {
        this.name = name;
        this.area = area;
        this.impression = impression;
    }

    // skiresortUpdateFormクラスのインスタンスからidを取得するため引数なし
    public String getName() {
        return name;
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
