package com.example.skiresortapi.service;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import com.example.skiresortapi.entity.Skiresort;

import java.util.List;

/**
 * スキーリゾートに関するサービスを提供するインターフェース
 */
public interface SkiresortService {
    List<Skiresort> findAll();

    Skiresort findById(int id);

    Skiresort createSkiresort(SkiresortCreateForm skiresortCreateForm);

    /**
     * 指定されたIDのスキーリゾートを更新する
     *
     * @param id
     * @param name
     * @param area
     * @param impression
     */
    void updateSkiresort(int id, String name, String area, String impression);

    void deleteSkiresort(int id);
}
