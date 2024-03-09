- モック:偽物。本物のフリをする。
- スタブ:代理。代わりのものを使う。

## 考え方

- ServiceがMapperに依存している状態なので、そのまま書くとエラー->単体テストなぼでMapperに依存できない
- 依存しているクラスをモック化する
- スタブ化(モック化)したMapperの状態を確認する
- DBは無関係
- `doReturn`:値を返すメソッドをスタブ化(モック化)した時に返す値を定義するためのメソッド

  モックオブジェクトが特定のメソッド呼び出しに対して、特定の値を返すように指定するために使用される

  モックオブジェクトがgetSomeValue()メソッドが呼ばれた時に42を返すように設定する例
  `doReturn(42).when(someMock).getSomeValue();`

- 検査例外と非検査例外:`throwshrows Exception`:必要？不要？->テストケース内で呼び出すメソッドが検査例外をthrowしうる場合必要

## 準備

- `@ExtendWith(MockitoExtension.class)`

  -> Mockitoを使用できるようにclassに付ける
- classに`@ExtendWith(MockitoExtension.class)` // JUnit5でMockitoを使うため
- `@Mock` // モック化（スタブ化）する対象に定義
- `@InjectMocks` // テスト対象に定義 @Mockでモックにしたインスタンスの注入先となるインスタンスに定義（インターフェースに付けるとエラー）

## エラー

- `staticでないメソッドdeleteSkiresort(int)をstaticコンテキストから参照することはできません`:インスタンスメソッドをstaticメソッドの呼び出し方で呼び出していたのでエラー ->
  staticメソッド = クラスメソッド

## 実装概要

- `throws Exception`:テスト対象が検査例外をthrowするかどうかで必要不要を判断する。Mapperの返す値によって例外を起こしそうな場合は必要
- `doReturn`:Mapperの動作をスタブ化している仮のデータを定義している
- `Skiresort actual`:実際の値が入る
- `assertThat`:テスト対象の実行結果やオブジェクトの状態を期待する値や条件と比較する
- `assertThat(actual)`:`assertThat`の引数`(actual)`に実際の値を定義する。Serviceが返した実際の値をassertThat(検証/比較)している
- `assertThat(actual).isEqualTo(期待値となる値)`:期待値は`.isEqualTo`の引数に定義する
- アサーションのimport文に気を付ける

## doReturnの書き方

- `when(モックインスタンス.メソッド(引数)).thenReturn(戻り値);`
- `doReturn(戻り値).when(モックインスタンス).メソッド(引数);`

## doNotingの書き方

```// モックインスタンスが呼ばれた時、何も返さない
doNothing().when(モックインスタンス).メソッド(任意の引数);
```

## doReturn or doNothing(deleteとinsert：どちらもvoidを返す)

- スタブ化するものに戻り値がある->doReturn
- `delete`:`Mapper`の`findByIdを`スタブ化している(findByIdには戻り値がある)-> IDを使ってSkiresortを探している
- `insert`:`Mapper=の`findById`は呼ばれていないので、findByIdをスタブ化する必要はない

---

- 存在するIDを指定した時、正常にデータが返されること
    - `doReturn -when`:スタブ化した`id1`のデータを定義する
    - `assertThat(actual).isEqualTo()`：`.isEqualTo`の引数に、期待値データを定義する
    - `verify`：1回だけ`id1`が呼び出されたかを確認する

- 全てのデータを取得する
    - リスト化する
    - `doReturn(戻り値).when(モック化するMapperインスタンス).テストしたいメソッド();`
    - `actual`:テストしたい実際の値をリスト型のactualに代入する

- 存在しないIDを指定した時、エラーメッセージが返されること
    - `throws Exception`:必要？不要？存在しないIDを指定した時にMapperは一体どんな値を返すのかを考える
    - `assertThatThrownBy()`:例外の検証ができる
    - `ResourceNotFoundException`:指定したIDに該当するリソースがないことを通知する例外

- 指定したIDの情報を更新できること

1. `skiresortMapper`の`findById`メソッドを使って更新前のデータを取得する ->`d`oReturn -when`:whenに更新前データを定義する
2. `skiresortServiceImpl`オブジェクトの`updateSkiresort`メソッドを呼び出す。このメソッドは、指定したIDのスキーリゾート情報を更新する->`Lake Louise`
3. `verify`:`skiresortMapper`オブジェクトのID1が1回呼ばれたことの検証。
4. 新しい`Skiresort`インスタンスを作成し、変数`updateSkiresort`に更新後データ`Lake Louise`を設定する。-> `Skiresort`
   のインスタンス化`updateSkiresort`を定義しないとエラー
5.
    - `verify`:`skiresortMapper`オブジェクトの`updateSkiresort`メソッドが1回呼ばれたことの検証
    - `verify`の検証時に`updateSkiresort`を渡す->`Mockito`は`skiresortMapper.updateSkiresort`に更新後の`Lake Louise`
      の情報が渡されたのだよねという検証までしてくれる

- updateSkiresortに存在しないIDを指定するとエラーメッセージが返される
    - `assertThatThrownBy`:例外の検証ができる。`ResourceNotFoundException`をthrowされることを期待している
    - `isInstanceOf`:throwされた例外が`ResourceNotFoundException`のインスタンスであることを検証する
- 指定したIDの情報を削除する
    - `doReturn`:対象のIDのスキー場情報をモック化して`when`で`skiresortMapper`で対象IDを検索する
    - `void`：`deleteSkiresort`はvoidのため、`assertThat`は使えない
    - staticでないメソッド`deleteSkiresort(int)`をstaticコンテキストから参照することはできませんエラーが表示->
      インスタンスメソッドをstaticメソッドの呼び出し方で呼び出していたのでエラー->staticメソッド = クラスメソッド
    - 戻り値はvoidなので検証しない。Mapperに渡されている値はverifyで検証する
- 新規登録
    - `insertSkiresort`は戻り値がvoidなので、returnするべきものがない
      ->`doNothing`を使う
     `````` // モックインスタンスが呼ばれた時、何も返さない
      doNothing().when(モックインスタンス).メソッド(任意の引数);```

1. `Skiresort`をインスタンス化。`createSkiresort`メソッドに渡すための実引数を定義する
2. `SkiresortCreateForm`をインスタンス化してぞれぞれの属性と値を設定する
3. `doNothing`で`skiresortMapper.insertSkiresort(skiresort);`をスタブ化する->実際には何も返さない
4. `skiresortServiceImpl.createSkiresort`の戻り値を、新しく作成した`skiresort`を`actual`に代入する。新規登録のテスト実行
5. `skiresortServiceImpl.createSkiresort`の戻り値である`Skiresort`の値が期待通りであるか->`actual`と`insertSkiresort`
   が等しいか`assertThat`で検証する
6. `verify`で`skiresortMapper.insertSkiresort`が1回呼ばれて引数に`Skiresort`が渡されていることを検証する

- `skiresortCreateForm`：リクエストボディのため属性にIDが含まれない。`name`,`area`,`impression`
  `actual`:`skiresortServiceImpl.createSkiresort(skiresortCreateForm)`の呼び出し結果が代入される==Skiresortのオブジェクト(
  ==skiresort) `assertThat`:`Skiresort`のオブジェクトと`actual`が等しいかを検証->つまり新規作成されたスキー場情報が`Skiresort`
  オブジェクトであるか？を検証している
- `Entity`クラス：DBのテーブルに対応するためIDが必要 `SkiresortCreateForm`クラス：FormはリクエストボディのためIDを持たない（FormはDBと対応していないため）

debug確認

- skiresortServiceImpl.createSkiresortが返すactialの値->actual = {Skiresort@4285} id = 0 name = "CoronetPeak" area = "NZ"
  impression = "初中級者の時に行ったので初めてのTバーに撃沈。岩だらけの広い氷山で日本にはないタイプのゲレンデ"
- assertThatで比較しているSkiresortオブジェクト->skiresort = {Skiresort@4191} id = 0 name = "CoronetPeal" area = "NZ"
  impression = "初中級者の時に行ったので初めてのTバーに撃沈。岩だらけの広い氷山で日本にはないタイプのゲレンデ"
- テストケースの命名:updateSkiresortとskiresortMapperメソッドに対するテスト
- テスト対象のメソッドが異なる->Whenが異なるが、存在しないIDを指定した時エラーメッセージが返されるかのテストをしている
- `Given`, `When`, `Then`を意識して考える
- `Given`(入力):データの生成やモックの設定のようなテストの準備
- `When`(実行):テスト対象のメソッドや動作の呼び出し
- `Then`(出力):出力や振る舞いが正しいかどうか検証するためのアサーションの実行

## 注意

- `doReturn`はスタブ化（仮のデータ）を記述しているので、テスト内容によってその限りではない。

- `doReturn`：Mapperの動作をスタブ化しているので、テスト期待値と等しくなる
  今回のコードの場合、Mapperが返した値をそのまま返すようなServiceの実装になっているのでこの記述で良いが
  Service内でMapperから取得した値を編集したりする場合この記述は正しくなくなる。

- このテストではdoReturnがたまたま期待値と一致したということであり
  実際の期待値は、`assertThat(actual).isEqualTo(期待値となる値)`と記述する。
