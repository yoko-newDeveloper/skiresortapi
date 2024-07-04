package com.example.skiresortapi.controller.response;

import com.example.skiresortapi.entity.Skiresort;

/**
 * 個人情報などレスポンス内容を制限したい場合に設定するクラス
 */
public class SkiresortResponse {
    private final String name;
    private final String area;
    private final String impression;

    public SkiresortResponse(Skiresort skiresort) {
        this.name = skiresort.getName();
        this.area = skiresort.getArea().getName(); // Areaオブジェクトのnameフィールドを取得
    }

    /**
     * スキーリゾート名を取得する
     *
     * @return スキーリゾート名
     */
    public String getName() {
        return this.name;
    }

    /**
     * スキーリゾートのエリアを取得する
     *
     * @return スキーリゾートエリア
     */
    public String getArea() {
        return this.area;
    }

    /**
     * スキーリゾートの印象を取得する
     *
     * @return スキーリゾートの印象
     */
    public String getImpression() {
        return this.impression;
    }
}
