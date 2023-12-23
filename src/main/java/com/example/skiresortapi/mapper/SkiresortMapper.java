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

    @Select("SELECT * FROM skiresort")
    List<Skiresort> findAll();

    @Select("SELECT * FROM skiresort WHERE id = #{id}")
    Optional<Skiresort> findById(int id);

    @Insert("INSERT INTO skiresort (id, name, area, impression) VALUES (#{id}, #{name}, #{area}, #{impression})")
    // idを自動生成する
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSkiresort(Skiresort skiresort);

    @Update("UPDATE skiresort SET name = #{name}, area = #{area}, impression = #{impression} WHERE id = #{id}")
    void updateSkiresort(Skiresort skiresort);

    @Delete("DELETE skiresort FROM skiresort WHERE id = #{id}")
    void deleteSkiresort(int id);
}
