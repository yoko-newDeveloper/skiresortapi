package com.example.skiresortapi.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * スキーリゾートの結合テスト
 */
@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkiresortRestApiIntegrationTest {

    /**
     * MocMvcを自動注入
     */
    @Autowired
    MockMvc mockMvc;

    /**
     * 全てのスキーリゾートを取得するテスト
     */
    @Nested
    class ReadAllTest {

        /**
         * スキーリゾート全てを取得する
         * テストデータセットをロードして、取得結果を比較する
         *
         * @throws Exception
         */

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @Transactional
        void スキーリゾートを全件取得した時ステータスコード200を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.get("/skiresorts"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    [
                        {
                            "name": "LakeLouise",
                            "area": "Canada"
                        },
                        {
                            "name": "Vail",
                            "area": "Colorado"
                        },
                        {
                            "name": "Zermatt",
                            "area": "Swiss"
                        }
                    ]
                    """, response, JSONCompareMode.STRICT);
        }
    }

    @Nested
    class ReadByIdTest {

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @Transactional
        void 存在するIDのスキーリゾートを取得した時ステータスコード200を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.get("/skiresorts/{id}", 3))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                        {
                            "name": "Zermatt",
                            "area": "Swiss"
                        }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @Transactional
        void 存在しないIDのスキーリゾートを取得した時ステータスコード404を返すこと() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/skiresorts/{id}", 99))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class CreateTest {

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @ExpectedDataSet(value = "datasets/it/create-skiresort.yml", ignoreCols = {"id"})
        @Transactional
        void 新規のスキーリゾートを登録した時ステータスコード201を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.post("/skiresorts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "name": "Whistler",
                                        "area": "Canada",
                                        "impression": "Obtained Canadian snowboard instructor license"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                        "name": "Whistler",
                        "area": "Canada"
                        }
                        """, response, JSONCompareMode.STRICT);
        }
    }

    @Nested
    class UpdateTest {

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @ExpectedDataSet(value = "datasets/it/update-skiresort.yml")
        @Transactional
        void 存在するIDを指定してスキーリゾート情報を更新するとステータスコード200を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/skiresorts/{id}", 3)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "id": 3,
                                        "name": "Treble Cone",
                                        "area": "New Zealand",
                                        "impression": "Features a long course with views of Lake Wanaka"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                        "message": "successfully update"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @Transactional
        void 存在しないIDのスキーリゾートを更新した時ステータスコード404を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/skiresorts/{id}", 100)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "name": "Blue Mountain",
                                        "area": "Canada",
                                        "impression": "All of the lodges and ski houses are cute, like a dreamland"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                        "path": "/skiresorts/100",
                        "status": "404",
                        "message": "skiresort not found",
                        "timestamp": "2024-03-10T20:48.123456789+09:00[JST/Tokyo]",
                        "error": "Not Found"
                    }
                    // timestampは比較対象外
                    """, response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", ((o1, o2) -> true))));
        }
    }

    @Nested
    class DeleteTest {

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @ExpectedDataSet(value = "datasets/it/delete-skiresort.yml")
        @Transactional
        void 存在するIDを指定してスキーリゾートを削除した時ステータスコード200を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.delete("/skiresorts/{id}", 3))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                        "message": "successfully deleted"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/it/skiresort.yml")
        @Transactional
        void 存在しないIDのスキーリゾートを削除した時ステータスコード404を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.delete("/skiresorts/{id}", 5))
                    .andExpect(status().isNotFound())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                        "path": "/skiresorts/5",
                        "status": "404",
                        "message": "skiresort not found",
                        "timestamp": "2024-03-10T22:00:00:123456789+09:00[JST/Tokyo]",
                        "error": "Not Found"
                    }
                                        
                    // timestampは比較対象外                    
                    """, response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", ((o1, o2) -> true))));
        }
    }
}
