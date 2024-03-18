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

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkiresortRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

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
                        "message": "resource not found",
                        "timestamp": "2024-03-10T20:48.123456789+09:00[JST/Tokyo]",
                        "error": "Not Found"
                    }
                    // timestampは比較対象外
                    """, response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", ((o1, o2) -> true))));
        }
    }
}
