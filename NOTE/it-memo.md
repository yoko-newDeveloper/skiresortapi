1. テストケース名：レスポンスのステータス、JSONを意識して決める

- `@Nested`でのクラス名:APIごとに分ける
- Read
- ReadAllTest
- ReadByIdTest

2. テストケースを考えるとき振る舞い駆動`Given/When/Then`を意識して文章を組み立てる
3. 振る舞いとはAPIがどんなレスポンスを返すか

- `Given`(前提条件):テストケースに入る前に状態は->なくてもよい
- `When`:どのような操作や入力があるのか
- `Then`:その操作や入力の後に期待すべき結果は何か

4. `MockMvc`:リクエストの検証`AutoConfigureMockMvc`:自動でMockMvcを行う
5. テキストブロック:`"""`ダブルウォーと3つでラップした文字列をひとつの塊として扱う(Java13以降)

## スキーリゾートを全件取得したときステータスコードが200を返すこと

- `@DataSet(value = "datasets/it-skiresort.yml")`:テスト初期データ。データセットなのでSkiresortで定義しているデータを設定すること
- `JSONAssert`:初期データとの比較。期待値を定義するが、HTTPレスポンスなのでSkiresortResponseで定義しているデータのみを設定すること

## 存在しないIDのスキーリゾートを取得した時ステータスコードが404エラーを返すこと

- `NOT_FOUND`(404):サーバがリクエストされたリソースを見つけられない。存在しないエンドポイント、リソースをリクエストした場合
- リクエストを行HTTPステータスコードのみ検証する:レスポンスの内容を検証する必要がないので、テスト初期値のみの設定で良い
- `/skiresorts/{id}, 99`:エンドポイントid99(存在しないID99)でスキーリゾートをリクエスト->`isNotFound()`400が適している

## 新しいスキーリゾートを登録する

- `@Dataset`:createの場合idは自動採番されるため、指定するとkey重複を起こす場合がある
- `@ExpectedDataSet`:テスト期待値
- `ignoreCols = "id"`:idの列を比較対象から除外する
- `isCreated()`:登録は201
- SkiresortControllerのcreateSkiresortメソッドに設定しているreturn部分を確認して、レスポンスする内容を設定する

### リクエストボディを書く

- `JSONAsserr.assertEquals`:JSON形式で新規登録する情報を全て記述する(SkiresortResponseのフィールドに合わせること)
