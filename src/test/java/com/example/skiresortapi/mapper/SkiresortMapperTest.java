package com.example.skiresortapi.mapper;

import com.example.skiresortapi.entity.Skiresort;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SkiresortMapperTest {

    @Autowired
    SkiresortMapper skiresortMapper;

    @Test
    @DataSet(value = "datasets/skiresort.yml")
    @Transactional
    void すべてのスキーリゾート情報が取得できること() {
        List<Skiresort> skiresorts = skiresortMapper.findAll();
        assertThat(skiresorts)
                .hasSize(3)
                .contains(
                        new Skiresort(1, "appi", "iwate", "残雪で8月くらいまで営業している"),
                        new Skiresort(2, "zao", "yamagata", "樹氷が絶景"),
                        new Skiresort(3, "hakubagoryu", "nagano", "インストラクターデビューしたスキー場")

                );
    }

    @Test
    @DataSet(value = "datasets/skiresort.yml")
    @Transactional
    void 指定したIDのスキーリゾート情報が取得できること() {
        assertThat(skiresortMapper.findById(1))
                .contains(new Skiresort(1, "appi", "iwate", "残雪で8月くらいまで営業している"));
    }
}
