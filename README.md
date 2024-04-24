# skiresortapiアプリケーション概要

スキーリゾートにおける名称・エリア・特徴についてのCRUDアプリケーション及びテスト
テストレポートは自動生成する

## ディレクトリ構成

```agsl
.
├── conf
│   └── mysql
│       └── my.cnf
├── gradlew/wrapper
├── sql
│ └── 001-create-table-and-load-data.sql
├── src
├── Dockerfile
├── docker-compose.yml
└── build.gradle
```

## レイヤー構成

```agsl
.
├── src/main/java/com.example.skiresortapi
	├── controller
	│   ├── SkiresortController
	│   │	└──form
	│   │	   └──SkiresortCreateForm
	│   └── response
	│	   └── SkiresortResponse
	├── entity
	│   └── Skiresort
	├── exception
	│   ├── CustomExceptionHandler
	│   └── ResourceNotFoundException
	│── mapper
	│   └── SkiresortMapper
	├── service
	│   ├── SkiresortService
	│   └── SkiresortServiceImpl
	├── repository
	│   └── SkiresortRepository
	└── build.gradle
 
```

## 存在しないidを指定した場合404エラーを返す

## 実装内容

### ブランチ：feature_1/database

- database:database周りを作成

### ブランチ：feature_３/read

- Read:skiresortテーブルの前データを取得する
- Read(ID):指定した既存IDのデータを取得する
- Create:新規ID5の仮データを作成

### ブランチ：feature_5/errorHandling

- ErrorHandling:存在しないIDを指定して取得する例外処理

### ブランチ：feature_7/update

- Update:ID5のname,impressionデータを更新する

### ブランチ：feature_9/exception

- ErrorHandling:nameに対する制御

### ブランチ：feature_11/delete

- Delete:IDを指定して1レコードを削除する

## 単体テスト

### ブランチ：feature_14/ut-mapper

- すべてのスキーリゾート情報を取得する
- 指定したIDのスキーリゾート情報を取得する
- レコードが存在しない場合に空のListを取得する
- 指定したIDの情報を更新する
- 指定したIDの情報を削除する
- 更新時に指定したIDが存在しないときテーブルのレコードが更新されない
- 削除時に指定したIDが存在しないときテーブルのレコードが削除されない

### ブランチ:feature_16/ut-service

- 存在するIDを指定した時に正常データが返されること
- 全てのスキーリゾート情報を取得できること
- 存在しないIDを指定した時にエラーメッセージが返されること
- 指定したIDのスキーリゾート情報を更新できること
- updateSkiresortメソッド:指定したIDが存在しない時にエラーメッセージを返されること
- 指定したIDのスキーリゾート情報を削除できること
- 新規スキーリゾート情報を登録する

### ブランチ:feature_18/ut-summary

テストの順番をSkiresortMapper,SkiresortServiceImplのそれぞれの順番に合わせる
Mapperテスト

1. findAll
    - すべてのスキーリゾート情報が取得できること
    - レコードが存在しない場合に空のListが取得できること
2. findById
    - 指定したIDのスキーリゾート情報が取得できること
3. insertSkiresort
    - 新規のスキーリゾートを登録できること
4. updateSkiresort
    - 指定したIDのスキーリゾート情報を更新できること
    - 更新時に指定したIDが存在しないときテーブルのレコードが更新されないこと
5. deleteSkiresort
    - 指定したIDのスキーリゾート情報を削除すること
    - 削除時に指定したIDが存在しないときテーブルのレコードが削除されないこと

Serviceテスト

1. findAll

    - 全てのスキーリゾート情報を取得できること

2. findById

    - 存在するスキーリゾートのIDを指定した時に正常にデータが返されること
    - 存在しないIDを指定した場合findByIdメソッドはエラーメッセージを返すこと

3. createSkiresort

    - 新規のスキーリゾート情報を登録できること

4. updateSkiresort

    - 指定したIDのスキーリゾート情報を更新できること
    - 存在しないIDを指定した場合updateSkiresortメソッドはエラーメッセージを返すこと

5. deleteSkiresort

    - 指定したIDのスキーリゾート情報を削除できること

## GitHubActions

### ブランチ:feature_20/gradle-test

GitHub Actionsでワークフローを自動化する

- `Hello World`を表示させるだけのコードを実装する

### ブランチ:feature_22/githubActions-gradleTest

- hello.ymlを単体テストを自動でビルドしてテストを行うように修正する

### ブランチ:feature_24/githubActions-gradleTestArtifact

- Artifactを使用したGradleテストを実行

### ブランチ:feature_26/validation

- name,area,customerEvaluationsのnull,空文字,ブランクを許可しない
  正常系テスト、異常系テストを実行する

## 結合テスト

### ブランチ:feature_28/it-getAll

- 全てのスキーリゾート情報を取得し、ステータスコード200が返されることのテスト

### ブランチ:feature_30/it-readById

- ReadByIdメソッドのテスト
    - 存在しないIDを取得し、ステータスコード404が返されることのテスト
    - 存在しないIDを取得し、ステータスコードが200が返されることのテスト

### ブランチ:feature_32/it-create

- 新しいスキーリゾートを登録する

### ブランチ:feature_34/it-update

- 存在するIDを指定した更新
- 存在しないIDを指定した更新

### ブランチ:feature_36/it-delete

- 存在するIDを指定した削除
- 存在しないIDを指定した削除

## ブランチ:feature_39/validationImplement

- CustomExceptionHandlerの整理

## ブランチ:feature_41/validator

- nameに関するバリデーション

## ブランチ:feature_43/ut-mapperTest-add

- ReadByIdメソッドのテストを追加

## ブランチ:feature_45/areaValidation

- areaに関するバリデーション

## ブランチ:feature_47/imperssionValidation

- impressionに関するバリデーション

## ブランチ:feature_49/ut-mapperTest-fix

- insertテストに期待値を追記

## ブランチ:feature_51/correlationRequiredValidation

- SkiresortCreateForm:登録時のバリデーションテスト
    - 登録時にはname,area,impression全ての項目の入力が必要である
- SkiresortPatchForm:更新時の相関項目のチェックテスト
    - 更新時にはname,area,impressionの全てを入力しなくてもバリデーションエラーとならないように実装

## ブランチ:feature_53/addComment

- メソッドにJavaDocコメントを追加

## ブランチ:feature_feature_55/eception-fix

- exceptionディレクトリのConstomExceptionHandlerを2つのクラスに分割する

## ブランチ:feature_57/errorMessage-fix

- エラーメッセージ`resource not found`を具体的な名前`skiresort not found`に変更
    - SkiresortRestApiIntegrationTest
    - SkiresortServiceImpl

## ブランチ:git checkout -b feature_59/add-repositoryDirectory

- repositoryディレクトリを作成

## 実装順理由

| 順番 | 機能                 | 理由                                                  |
|:--:|--------------------|-----------------------------------------------------|
| 1  | Read               | テーブルの全てのデータを取得してから開始するため                            |
| 2  | Read(id)           | idに関するErrorHandlingを実装するため                          |
| 3  | idのErrorHandling   | アプリケーションの安定性と信頼性を向上させるために重要な要素であり、早い段階で実装するべきと考えたため |
| 4  | Update             | Createした仮データを完成させるため                                |
| 5  | nameのErrorHandling | 早い段階で実装すべきだが、Update作成後にテストする必要があるため                 |
| 6  | Delete             | 実装予定機能が完了してからDeleteするため                             |

## curlコマンド

### 正常確認

- 取得確認

`-X GET http://localhost:8080/skiresorts -i`

- 新規作成(id5)

```
curl -i -X POST -H "Content-Type: application/json" -d '{
  "id": 5,
  "name": "Skiresort Name",
  "area": "Skiresort Area",
  "impression": "Skiresort Impression"
}' <http://localhost:8080/skiresorts>
```

- 更新(id5)

```agsl
% curl -X PATCH -H "Content-Type: application/json" -d '{
   "name":"nozawa-onsen",
   "area":"nagano",
   "customerEvaluation":"ゴンドラが10人乗りでガラス張りに変わった。外国人ばかりで激混み"
   }' http://localhost:8080/skiresorts/5 -i
```

- LocationHeader

```agsl
curl -i -X POST -H "Content-Type: application/json" -d '{
"name":"Skiresort Name",
"area":"Skiresort Area",
"impression":"impression"
}' http://localhost:8080/skiresorts
```

- Delete(id6)

`% curl -i -X DELETE http://localhost:8080/skiresorts/6`

### 例外確認

- 存在しないID99を指定

`curl http://localhost:8080/skiresorts/99 -i`

- 存在しないID55を指定

`curl http://localhost:8080/skiresorts/55 -i`

- 存在しないid10を更新

```agsl
curl -X PATCH -H "Content-Type: application/json" -d '{
"name":"hoge",
"area":"hoge",
"impression":"hoge"
}' http://localhost:8080/skiresorts/10 -i
```

## 動作確認ポイント

| 項目           | レスポンス                |
|--------------|----------------------|
| HTTP         | 1.1 404              |
| Content-Type | application/json     |
| error        | Not Found"           |
| message      | "resource not found" |
| status       | "404"                |
| path         | "99"/"55"            |

## 動作確認キャプチャ

![2C477B19-77AE-481D-96CC-716618B99999_1_201_a](https://github.com/yoko-newDeveloper/raiseTech-course-task10/assets/91002836/172039f9-cf55-4381-8d2e-68cee9890da7)
