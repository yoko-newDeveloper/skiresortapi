package com.example.skiresortapi.controller.response;

import com.example.skiresortapi.entity.Skiresort;

// 個人情報などレスポンス内容を制限したい場合に設定するクラス
public class SkiresortResponse {
    private final String name;
    private final String area;

    public SkiresortResponse(Skiresort skiresort) {
        this.name = skiresort.getName();
        this.area = skiresort.getArea();
    }

    public String getName() {
        return this.name;
    }

    public String getArea() {
        return this.area;
    }
}
