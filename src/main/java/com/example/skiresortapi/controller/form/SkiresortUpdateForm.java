package com.example.skiresortapi.controller.form;

import org.jetbrains.annotations.NotNull;

public class SkiresortUpdateForm {

    @NotNull
    private final String name;
    @NotNull
    private String area;
    @NotNull
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
