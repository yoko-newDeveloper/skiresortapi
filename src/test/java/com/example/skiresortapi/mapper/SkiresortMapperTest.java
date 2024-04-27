package com.example.skiresortapi.mapper;

import com.example.skiresortapi.entity.Skiresort;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class FindAllTest {

        /**
         * 全てのスキーリゾート情報が取得できること
         */
        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
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
        @DataSet(value = "datasets/ut/empty-skiresort.yml")
        @Transactional
        void レコードが存在しない場合に空のListが取得できること() {
            assertThat(skiresortMapper.findAll().isEmpty());
        }
    }

    /**
     * SkiresortMapperのFindByIdメソッドをテストするクラス
     */
    @Nested
    class FindByIdTest {

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        @Transactional
        void 指定したIDのスキーリゾート情報が取得できること() {
            assertThat(skiresortMapper.findById(1))
                    .contains(new Skiresort(1, "appi", "iwate", "残雪で8月くらいまで営業している"));
        }
    }

    /**
     * スキーリゾートの更新に関するテストクラス
     */
    @Nested
    class CreateSkiresortTest {

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        // IDは比較対象外
        @ExpectedDataSet(value = "datasets/ut/create-skiresort.yml", ignoreCols = "id")
        @Transactional
        void 新規のスキーリゾートを登録できること() {
            // IDは指定しない
            Skiresort skiresort = new Skiresort("yunomaru", "nagano", "道路を渡ったところに急斜面がある");
            skiresortMapper.insertSkiresort(skiresort);
        }
    }

    /**
     * スキーリゾートの更新に関するテストクラス
     */

    @Nested
    class UpdateSkiresortTest {

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        @ExpectedDataSet(value = "datasets/ut/update-skiresort.yml")
        @Transactional
        void 指定したIDのスキーリゾート情報を更新できること() {
            Skiresort skiresort = new Skiresort(3, "hakubaiwatake", "nagano", "全コース滑るとオートマチックにレベルアップできる不思議なゲレンデ");
            skiresortMapper.updateSkiresort(skiresort);
        }

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        @ExpectedDataSet(value = "datasets/ut/noUpdate-skiresort.yml")
        @Transactional
        void 更新時に指定したIDが存在しないときテーブルのレコードが更新されないこと() {
            Skiresort skiresortUpdate = new Skiresort(4, "takasu", "gifu", "いつも混んでいる人気のゲレンデ");
            skiresortMapper.updateSkiresort(skiresortUpdate);
        }
    }

    /**
     * スキーリゾートの削除に関するテストクラス
     */
    @Nested
    class DeleteSkiresortTest {

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        @ExpectedDataSet(value = "datasets/ut/delete-skiresort.yml")
        @Transactional
        void 指定したIDのスキーリゾート情報を削除すること() {
            skiresortMapper.deleteSkiresort(3);
        }

        @Test
        @DataSet(value = "datasets/ut/skiresort.yml")
        @ExpectedDataSet(value = "datasets/ut/noDelete-skiresort.yml")
        @Transactional
        void 削除時に指定したIDが存在しないときテーブルのレコードが削除されないこと() {
            skiresortMapper.deleteSkiresort(4);
        }
    }
}
