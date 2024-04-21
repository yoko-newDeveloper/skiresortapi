package com.example.skiresortapi.service;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.exception.ResourceNotFoundException;
import com.example.skiresortapi.mapper.SkiresortMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * スキーリゾートService
 */
@Service
public class SkiresortServiceImpl implements SkiresortService {

    // field(MapperをServiceで使えるようにする)
    private final SkiresortMapper skiresortMapper;

    // constructor(MapperをServiceで使えるようにする)
    public SkiresortServiceImpl(SkiresortMapper skiresortMapper) {
        this.skiresortMapper = skiresortMapper;
    }

    /**
     * 全てのスキーリゾートを取得
     *
     * @return 取得したスキーリゾート
     */
    @Override
    public List<Skiresort> findAll() {
        return skiresortMapper.findAll();
    }

    /**
     * 指定したIDのスキーリゾート情報を取得する
     *
     * @param id 取得したスキーリゾート情報のID
     * @return スキーリゾート情報
     */
    @Override
    public Skiresort findById(int id) {
        Optional<Skiresort> skiresort = this.skiresortMapper.findById(id);
        return skiresort.orElseThrow(() -> new ResourceNotFoundException("skiresort not found"));
    }

    /**
     * 新規スキーリゾートの登録
     *
     * @param skiresortCreateForm 登録するスキーリゾート情報のフォーム
     * @return 登録したスキーリゾート
     */
    @Override
    @Transactional
    public Skiresort createSkiresort(SkiresortCreateForm skiresortCreateForm) {
        Skiresort skiresort = new Skiresort(
                0, // idの仮初期値として設定
                skiresortCreateForm.getName(),
                skiresortCreateForm.getArea(),
                skiresortCreateForm.getImpression()
        );

        skiresortMapper.insertSkiresort(skiresort);
        return skiresort;
    }

    /**
     * スキーリゾートを更新する
     *
     * @param id         更新するスキーリゾートID
     * @param name       更新するスキーリゾート名
     * @param area       更新するスキーリゾートエリア
     * @param impression 更新するスキーリゾートの印象
     */
    @Override
    @Transactional
    public void updateSkiresort(int id, String name, String area, String impression) {
        Skiresort skiresort = this.skiresortMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("skiresort not found"));
        skiresort.setName(name);
        skiresort.setArea(area);
        skiresort.setImpression(impression);

        this.skiresortMapper.updateSkiresort(skiresort);
    }

    /**
     * スキーリゾートを削除する
     *
     * @param id 削除対象スキーリゾートのID
     */
    @Override
    @Transactional
    public void deleteSkiresort(int id) {
        // 指定されたIDが見つからない場合に例外をスローする
        // skiresort変数に格納せずに、直接skiresortMapper.findById(id)の結果を返す
        skiresortMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("skiresort not found"));
        skiresortMapper.deleteSkiresort(id);
    }
}
