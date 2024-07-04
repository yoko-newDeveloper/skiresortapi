package com.example.skiresortapi.service;

import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.mapper.SkiresortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * スキーリゾートServiceを提供する実装クラス
 */
@Service
public class SkiresortServiceImpl implements SkiresortService {

    // スキーリゾートのデータベース操作を行うMapper
    private final SkiresortMapper skiresortMapper;

    @Autowired
    public SkiresortServiceImpl(SkiresortMapper skiresortMapper) {
        this.skiresortMapper = skiresortMapper;
    }

    /**
     * 全てのスキーリゾートを取得
     *
     * @return スキーリゾート情報のリスト
     */
    @Override
    public List<Skiresort> findAll() {
        return skiresortMapper.findAll();
    }

    /**
     * 指定したIDのスキーリゾート情報を取得
     *
     * @param id 取得するスキーリゾート情報のID
     * @return 取得対象IDのスキーリゾート情報
     */
    @Override
    public Optional<Skiresort> findById(int id) {
        return skiresortMapper.findById(id);
    }

    /**
     * 新規スキーリゾート情報をデータベースに登録
     *
     * @param skiresort 登録するスキーリゾート情報
     */
    @Override
    public void insertSkiresort(Skiresort skiresort) {
        skiresortMapper.insertSkiresort(skiresort);
    }

    /**
     * スキーリゾート情報の更新
     *
     * @param skiresort 更新するスキーリゾート情報
     */
    @Override
    public void updateSkiresort(Skiresort skiresort) {
        skiresortMapper.updateSkiresort(skiresort);
    }

    /**
     * スキーリゾート情報の削除
     *
     * @param id 削除対象のスキーリゾート
     */
    @Override
    public void deleteSkiresort(int id) {
        skiresortMapper.deleteSkiresort(id);
    }
}
