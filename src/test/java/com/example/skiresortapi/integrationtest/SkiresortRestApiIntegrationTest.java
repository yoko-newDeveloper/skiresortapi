package com.example.skiresortapi.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
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
    }
}
