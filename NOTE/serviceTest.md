- モック:偽物。本物のフリをする。
- スタブ:代理。代わりのものを使う。

## 考え方

- ServiceがMapperに依存している状態なので、そのまま書くとエラー->単体テストなぼでMapperに依存できない
- 依存しているクラスをモック化する
- スタブ化(モック化)したMapperの状態を確認する
- DBは無関係
- doReturn:値を返すメソッドをスタブ化(モック化)した時に返す値を定義するためのメソッド

  モックオブジェクトが特定のメソッド呼び出しに対して、特定の値を返すように指定するために使用される

  モックオブジェクトがgetSomeValue()メソッドが呼ばれた時に42を返すように設定する例
  `doReturn(42).when(someMock).getSomeValue();`

## 準備

- `@ExtendWith(MockitoExtension.class)`

  -> Mockitoを使用できるようにclassに付ける
- classに`@ExtendWith(MockitoExtension.class)` // JUnit5でMockitoを使うため
- `@Mock` // モック化（スタブ化）する対象に定義
- `@InjectMocks` // テスト対象に定義 @Mockでモックにしたインスタンスの注入先となるインスタンスに定義（インターフェースに付けるとエラー）

## 実装

- `doReturn`:Mapperの動作をスタブ化している仮のデータを定義している
- `Skiresort actual`:実際の値が入る
- `assertThat`:テスト対象の実行結果やオブジェクトの状態を期待する値や条件と比較する
- `assertThat(actual)`:`assertThat`の引数`(actual)`に実際の値を定義する。Serviceが返した実際の値をassertThat(検証/比較)している
- `assertThat(actual).isEqualTo(期待値となる値)`:期待値は`.isEqualTo`の引数に定義する
- アサーションのimport文に気を付ける
- 存在するidを指定した時、正常にデータが返されること
    - `doReturn -when`:スタブ化した`id1`のデータを定義する
    - `assertThat(actual).isEqualTo()`：`.isEqualTo`の引数に、期待値データを定義する
    - `verify`：1回だけ`id1`が呼び出されたかを確認する

## 注意

- `doReturn`はスタブ化（仮のデータ）を記述しているので、テスト内容によってその限りではない。

- `doReturn`：Mapperの動作をスタブ化しているので、テスト期待値と等しくなる
  今回のコードの場合、Mapperが返した値をそのまま返すようなServiceの実装になっているのでこの記述で良いが
  Service内でMapperから取得した値を編集したりする場合この記述は正しくなくなる。

- このテストではdoReturnがたまたま期待値と一致したということであり
  実際の期待値は、`assertThat(actual).isEqualTo(期待値となる値)`と記述する。
