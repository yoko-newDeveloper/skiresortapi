# テスト結果レポート

- テスト結果レポート作成方法

```agsl
./gradlew test --tests "com.example.skiresortapi.SkiresortapiApplicationTests" --info
```

- コマンド実行結果から探す

```agsl
Generating HTML test report...
Finished generating test html results (0.026 secs) into: /Users/yoko/git/raiseTech-course/raiseTech-course-task10/build/reports/tests/test
```

- htmlファイルが作成されているので、ディレクトリで`index.html`を探す

# テストのグループ化

- グループごとにクラス分けする
- 共通のデータで分ける
- 共通の状態で分ける
- コンストラクタのテストを分ける
- それぞれのケースにメソッド名を使用したクラス分けをしてネストする

# テストコードの順番

- SkiresortMapperTest:SkiresortMapperに合わせる
- SkiresortServiceTest:SkiresortServiceに合わせる

## Enclosed

- Enclosed:JUnit4sで使用され、テストの構造を整理するために使われる。テストクラスをグループ化する。
- `@RunWith(Enclosed.class)`:クラスにアノテーションをつける

## NestedRunner

- NestedRunner:JUnit5で使用され、ネストしたテストをサポートする
- `@Mested`:クラスにアノテーションをつける

#テストケースの命名について
単体テストの場合、メソッド名に含めた方が良い

- `Given`,`When`,`Then`を意識して考える
    - `Given`(入力):データの生成やモックの設定のようなテストの準備
    - `When`(実行):テスト対象のメソッドや動作の呼び出し
    - `Then`(出力):出力や振る舞いがただしいかどうか検証するためのアサーションの実行

# カバレッジ

ホワイトボックスにおけるカバレッジ

- CO(命令網羅):それぞれ全ての命令文が実行されるようにテストを設計する
- C1(分岐網羅):条件分がどのように動作するかのテストを設計する
- C2(条件網羅):条件・判定のカバレッジ。複雑な条件分岐が全て動作するかのテストを設計する
- カバレッジレポートを使用する
