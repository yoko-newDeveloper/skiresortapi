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

    /**
     * スキーリゾート名を取得する
     *
     * @return スキーリゾート名
     */
    public String getName() {
        return this.name;
    }

    public String getArea() {
        return this.area;
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

    /**
     * @AssertTrue:相関項目のチェック
     * @return:falseの際にバリデーションを返す
     */
    @AssertTrue(message = "name, area, impressionのいずれかを入力してください")
    public boolean isNameOrAreaOrImpression() {
        // name,area,impressionの全てがnullまたは空文字または半角スペースの時にfalse(バリデーション)を返す
        return isNotBlank(this.name) || isNotBlank(this.area) || isNotBlank(this.impression);
    }

    /**
     * 空白チェック
     *
     * @param value チェック項目
     * @return true:空白ではない false:空白
     */
    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}
