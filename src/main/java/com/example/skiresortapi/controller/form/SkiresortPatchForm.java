package com.example.skiresortapi.controller.form;

import jakarta.validation.constraints.AssertTrue;

public class SkiresortPatchForm {

    private final String name;
    private String area;
    private String impression;

    public SkiresortPatchForm(String name, String area, String impression) {
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

    @AssertTrue(message = "name, area, impressionのいずれかを入力してください")
    public boolean isNameOrAreaOrImpression() {
        // name,area,impressionの全てがnullまたは空文字または半角スペースの時にfalse(バリデーション)を返す
        return (name != null && !name.isBlank()) || (area != null && !area.isBlank()) || (impression != null && !impression.isBlank());
    }
}
