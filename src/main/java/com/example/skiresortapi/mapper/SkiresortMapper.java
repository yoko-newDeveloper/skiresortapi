package com.example.skiresortapi.mapper;

import com.example.skiresortapi.entity.Skiresort;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SkiresortMapper {

    /**
     * 全てのスキーリゾートを取得する
     *
     * @return スキーリゾート情報のリスト
     */
    @Select("SELECT * FROM skiresort")
    List<Skiresort> findAll();

    /**
     * 指定したIDのスキーリゾート情報を取得する
     *
     * @param id 取得するスキーリゾートのID
     * @return 取得対象IDのスキーリゾート情報
     */
    @Select("SELECT * FROM skiresort WHERE id = #{id}")
    Optional<Skiresort> findById(int id);


    /**
     * 新規スキーリゾートをデータベースに登録する
     *
     * @param skiresort 登録するスキーリゾート情報
     */
    @Insert("INSERT INTO skiresort (id, name, area, impression) VALUES (#{id}, #{name}, #{area}, #{impression})")
    // idを自動生成する
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSkiresort(Skiresort skiresort);

    /**
     * スキーリゾート情報を更新する
     *
     * @param skiresort 更新するスキーリゾート情報
     */
    @Update("UPDATE skiresort SET name = #{name}, area = #{area}, impression = #{impression} WHERE id = #{id}")
    void updateSkiresort(Skiresort skiresort);

    /**
     * 指定したIDのスキーリゾート情報を削除する
     *
     * @param id 削除対象のスキーリゾート
     */
    @Delete("DELETE skiresort FROM skiresort WHERE id = #{id}")
    void deleteSkiresort(int id);
}
