package com.example.skiresortapi.service;

import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.exception.ResourceNotFoundException;
import com.example.skiresortapi.mapper.SkiresortMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    public void 全てのスキーリゾート情報を取得できること() {

        List<Skiresort> skiresorts = List.of(
                new Skiresort(1, "Cadrona", "NZ", "パイプのnationals公式大会で優勝して、副賞モルディブ1週間旅行だった！ゲレンデはコンクリートみたいに硬い"),
                new Skiresort(2, "Whistler", "canada", "滞在2週間の半分以上雨で、記録的な少雪な年だった"),
                new Skiresort(3, "Mt.Hood", "Oregon", "標高が高すぎて高山病になった。ガスってる日に2000m以上続く急斜面で滑落した"));

        // 依存しているSkiresortMapperをモック化する
        // skiresortMapperのfindAl()メソッドが呼ばれた時にskiresortsリストを返す
        doReturn(skiresorts).when(skiresortMapper).findAll();

        // test実行
        List<Skiresort> actual = skiresortServiceImpl.findAll();
        // actual(実際)の値がモック化した値(skiresorts)と等しいか検証する
        assertThat(actual).isEqualTo(skiresorts);
        verify(skiresortMapper, times(1)).findAll();
    }

    @Test
    void 存在しないIDを指定した時エラーメッセージが返されること() throws Exception {
        // モック化:ID100を指定した時に空かどうか
        doReturn(Optional.empty()).when(skiresortMapper).findById(100);

        // test実行:memberServiceImpl.findByIdメソッドにID100を渡した時、例外をスローすることを期待している
        assertThatThrownBy(() -> skiresortServiceImpl.findById(100)) // テスト対象メソッド
                // throwされる例外がResourceNotFoundException（リソースがないことを通知する）を返す
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("resource not found");
    }

    @Test
    void 指定したIDのスキーリゾート情報を更新できること() {
        // モック化:returnするSkiresortは更新前のデータを設定
        doReturn(Optional.of(new Skiresort(1, "Whistler", "Canada", "11kmのロングランが楽しめる。次回は天気の良いハイシーズンに行きたい"))).when(skiresortMapper).findById(1);
        // updateSkiresortメソッドを呼び出して、ID1が持つ情報をLake Louiseに更新する
        skiresortServiceImpl.updateSkiresort(1, "Lake Louise", "Canada", "バンフから近くて無料シャトルバスがある。広大で美しいゲレンデ");

        // skiresortMapperオブジェクトのID1が1回呼ばれたことの検証
        verify(skiresortMapper, times(1)).findById(1);

        // Skiresortのインスタンス定義
        // これが更新後のデータの期待値 Lake Louise->Skiresortのインスタンス化updateSkiresortを定義しないとエラー
        Skiresort updateSkiresort = new Skiresort(1, "Lake Louise", "Canada", "バンフから近くて無料シャトルバスがある。広大で美しいゲレンデ");
        // skiresortMapperオブジェクトのupdateSkiresortByIdメソッドが1回呼ばれたことの検証
        // skiresortMapperのupdateSkiresortメソッドの引数updateSkiresort変数が渡されて、更新後データであるLake Louiseであることを検証する
        verify(skiresortMapper, times(1)).updateSkiresort(updateSkiresort);
    }
}
