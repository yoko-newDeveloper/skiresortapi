package com.example.skiresortapi.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 * スキーリゾートを登録フォームクラス
 */
public class SkiresortCreateForm {

    @Size(min = 1, max = 20)
    @NotBlank
    private String name;

    @NotNull
    private int areaId;

    @NotNull
    private int impressionId;

    public SkiresortCreateForm(String name, int areaId, int impressionId) {
        this.name = name;
        this.areaId = areaId;
        this.impressionId = impressionId;
    }

    /**
     * スキーリゾート名を取得する
     *
     * @return スキーリゾート名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * スキーリゾートのエリアIDを取得する
     *
     * @return スキーリゾートのエリアID
     */
    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * スキーリゾートの印象IDを取得する
     *
     * @return スキーリゾートの印象ID
     */
    public int getImpressionId() {
        return this.impressionId;
    }

    public void setImpressionId(int impressionId) {

        this.impressionId = impressionId;
    }
}
