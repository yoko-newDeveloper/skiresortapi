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

/**
 * スキーリゾートMapper
 */
@Mapper
public interface SkiresortMapper {

    /**
     * 全てのスキーリゾート情報の取得
     *
     * @return スキーリゾート情報のリスト
     */
    @Select("SELECT skiresort.id, skiresort.name, area.id as area_id, area.name as area_name, " +
            "impression.id as impression_id, impression.description as impression_description " +
            "FROM skiresort " +
            "JOIN area ON skiresort.area_id = area.id " +
            "JOIN impression ON skiresort.impression_id = impression_id")
    List<Skiresort> findAll();

    /**
     * 指定したIDのスキーリゾート情報を取得する
     *
     * @param id 取得するスキーリゾートのID
     * @return 取得対象のスキーリゾート情報
     */
    @Select("SELECT skiresort.id, skiresort.name, area.id impression.id, impression.description " +
            "impression.id as impression_id, impression.description as impression_decription " +
            "FROM skiresort " +
            "JOIN area ON skiresort.area_id = area.id " +
            "JOIN impression ON skiresort.impression_id = impression.id " +
            "WHERE skiresort.id = #{id}")
    Optional<Skiresort> findById(int id);

    /**
     * 新規スキーリゾートをデータベースに登録する
     *
     * @param skiresort 登録するスキーリゾート情報
     */
    @Insert("INSERT INTO skiresort (id, name, area_id, impression_id) " +
            "VALUES (#{name}, #{area.id}, #{impression.id})")
    // idを自動生成する
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSkiresort(Skiresort skiresort);

    /**
     * スキーリゾート情報を更新する
     *
     * @param skiresort 更新するスキーリゾート情報
     */
    @Update("UPDATE skiresort SET name = #{name}, area_id = #{area.id}, impression_id = #{impression.id} " +
            "WHERE id = #{id}")
    void updateSkiresort(Skiresort skiresort);

    /**
     * 指定したIDのスキーリゾート情報を削除する
     *
     * @param id 削除対象のスキーリゾート
     */
    @Delete("DELETE skiresort FROM skiresort WHERE id = #{id}")
    void deleteSkiresort(int id);
}
