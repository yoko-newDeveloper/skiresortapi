package com.example.skiresortapi.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkiresortRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

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
}
