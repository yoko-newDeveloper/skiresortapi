package com.example.skiresortapi.service;

import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.mapper.SkiresortMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // JUnit5でMpckitoを使うために必要
class SkiresortServiceImplTest {

    @InjectMocks // テスト対象
    SkiresortServiceImpl skiresortServiceImpl;

    @Mock // モック化(スタブ化)したいインスタンスに定義
    SkiresortMapper skiresortMapper;

    @Test
    public void 存在するスキーリゾートのIDを指定した時に正常データが返されること() throws Exception {
        // doReturn -when :Mokietoの記述
        doReturn(Optional.of(new Skiresort(1, "zao", "yamagata", "樹氷が絶景で温泉も有名"))).when(skiresortMapper).findById(1);

        // テストの実行
        Skiresort actual = skiresortServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Skiresort(1, "zao", "yamagata", "樹氷が絶景で温泉も有名"));
        verify(skiresortMapper, times(1)).findById(1);

    }
}
