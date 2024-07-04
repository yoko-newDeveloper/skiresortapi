package com.example.skiresortapi.controller.form;

import jakarta.validation.constraints.AssertTrue;

/**
 * スキーリゾートの更新フォームのクラス
 */
public class SkiresortPatchForm {

    private final String name;
    private int areaId;
    private int impressionId;

    public SkiresortPatchForm(String name, int areaId, int impressionId) {
        this.name = name;
        this.areaId = areaId;
        this.impressionId = impressionId;
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

    /**
     * スキーリゾートのエリアIDを取得する
     *
     * @return スキーリゾートエリア
     */
    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * スキーリゾートに対する印象IDを取得する
     *
     * @return スキーリゾートの印象ID
     */
    public int getImpressionId() {
        return this.impressionId;
    }

    public void setImpressionId(int impressionId) {
        this.impressionId = impressionId;
    }

    /**
     * @AssertTrue:相関項目のチェック(name,areaId,impressionIdのいずれかが入力されていることを確認する)
     * @return:falseの際にバリデーションを返す
     */
    @AssertTrue(message = "name, areaId, impressionIdのいずれかを入力してください")
    public boolean isNameOrAreaOrImpression() {
        // nameが空白でない、またはareaIdが0より大きい、またはimpressionIdが0より大きい場合にtrueを返す
        return isNotBlank(this.name) || this.areaId > 0 || impressionId > 0;
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
