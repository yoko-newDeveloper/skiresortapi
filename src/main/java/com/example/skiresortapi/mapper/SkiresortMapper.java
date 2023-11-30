package com.example.skiresortapi.mapper;

import com.example.skiresortapi.entity.Skiresort;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SkiresortMapper {

    @Select("SELECT * FROM skiresortapi")
    List<Skiresort> findAll();

    @Select("SELECT * FROM skiresortapi WHERE id = #{id}")
    Skiresort findById(int id);

    @Insert("INSERT INTO skiresortapi (name, area, impression) VALUES (#{name}, #{area}, #{impression})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSkiresort(Skiresort skiresort);
}
