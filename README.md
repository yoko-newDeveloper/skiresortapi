# skiresortapiアプリケーション概要

このアプリケーションは、の情報を管理するためのCRUDアプリケーションです。<br>
名称、エリア、特徴などの情報を登録、更新、削除することができます。

- データベース周りの作成
- skiresortテーブルのデータ取得
- 存在しないIDを指定して取得する例外処理
- ID5のnameとimpressionデータの更新
- name、area、impressionのバリデーションテスト
- nameに関するバリデーション
- areaに関するバリデーション
- impressionに関するバリデーション
- 登録時のバリデーションテスト、更新時の相関項目のチェックテスト
- テストレポートの自動生成

更に、スキーリゾートの情報を正確に管理し、適切なバリデーションを行うことでデータの信頼性を高めることができます。

## 目次

1. [データベース周りの作成](#データベース周りの作成)
2. [skiresort テーブルのデータ取得](#skiresort-テーブルのデータ取得)
3. [存在しない ID を指定して取得する例外処理](#存在しない-id-を指定して取得する例外処理)
4. [ID5のname,impressionデータを更新](#id5のnameimpressionデータを更新)
5. [ID5のname,impressionデータを更新](#id5のnameimpressionデータを更新)
6. [IDを指定して1レコードを削除する](#idを指定して1レコードを削除する)
7. [スキーリゾート情報の取得、更新、削除などの単体テスト](#スキーリゾート情報の取得更新削除などの単体テスト)
8. [スキーリゾート情報の取得、更新、削除などサービスの単体テスト](#スキーリゾート情報の取得更新削除などサービスの単体テスト)
9. [SkiresortMapperとSkiresortServiceImplの化単体テストを順番に実行する](#skiresortmapperとskiresortserviceimplの化単体テストを順番に実行する)
10. [GitHub Actionsでワークフローを自動化する準備段階で、`Hello World`を表示するコードを実装](#github-actionsでワークフローを自動化する準備段階でhello-worldを表示するコードを実装)
11. [単体テストを自動でビルドしてテストを行うようにGradleを設定](#単体テストを自動でビルドしてテストを行うようにgradleを設定)
12. [Gradleを使用した単体テストをArtifactを使用して実行](#gradleを使用した単体テストをartifactを使用して実行)
13. [name,area,impressionのバリデーションテスト](#nameareaimpressionのバリデーションテスト)
14. [全てのスキーリゾート情報を取得し、ステータスコード200が返されることの結合テスト](#全てのスキーリゾート情報を取得しステータスコード200が返されることの結合テスト)
15. [存在しないIDを取得した時のエラーメッセージの結合テスト](#存在しないidを取得した時のエラーメッセージの結合テスト)
16. [スキーリゾート情報を登録する結合テスト](#スキーリゾート情報を登録する結合テスト)
17. [存在するID、存在しないIDを指定した更新の結合テスト](#存在するid存在しないidを指定した更新の結合テスト)
18. [存在するIDを指定した削除、更新の結合テスト](#存在するidを指定した削除更新の結合テスト)
19. [CustomExceptionHandlerの整理](#customexceptionhandlerの整理)
20. [nameに関するバリデーション](#nameに関するバリデーション)
21. [ReadByIdメソッドのテストを追加](#readbyidメソッドのテストを追加)
22. [areaに関するバリデーション](#areaに関するバリデーション)
23. [impressionに関するバリデーション](#impressionに関するバリデーション)
24. [insertテストに期待値を追記](#insertテストに期待値を追記)
25. [登録時のバリデーションテスト、更新時の相関項目のチェックテスト](#登録時のバリデーションテスト更新時の相関項目のチェックテスト)
26. [メソッドにJavaDocコメントを追加](#メソッドにjavadocコメントを追加)
27. [exceptionディレクトリのCustomExceptionHandlerを2つのクラスに分割](#exceptionディレクトリのcustomexceptionhandlerを2つのクラスに分割)
28. [エラーメッセージを具体的な名前に変更](#エラーメッセージを具体的な名前に変更)
29. [repositoryディレクトリを作成](#repositoryディレクトリを作成)
30. [repositoryクラスを作成](#repositoryクラスを作成)
31. [クラスコメント、JavaDocコメントを追加](#クラスコメントjavadocコメントを追加)
32. [READMEを整理](#readmeを整理)
33. [XMLファイルを追加](#Mapper.xmlファイルを追加 )

...

## ディレクトリ構成

```
.
├── conf
│   └── mysql
│       └── my.cnf
├── gradlew/wrapper
├── sql
│ └── 001-create-table-and-load-data.sql
├── src
├── Dockerfile
└── docker-compose.yml
```

## レイヤー構成

```
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
	└── scr/main/resources
	    └── com/examplt/skiresortapi/mapper/SkiresortMapper.xml
       
 
```

## 実装内容

| ブランチ名                                       | 機能                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| feature_1/database                          | データベース周りの作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| feature_3/read                              | skiresort テーブルのデータ取得<li>Read:skiresortテーブルの前データを取得する</li><li>指定したIDのスキーリゾート情報を取得する</li><li>Create:新規ID5の仮データを作成</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| feature_5/errorHandling                     | 存在しない ID を指定して取得する例外処理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| feature_5/errorHandling                     | 存在しないIDを指定して取得する例外処理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| feature_7/update                            | ID5のname,impressionデータを更新                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_9/exception                         | ID5のname,impressionデータを更新                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_11/delete                           | IDを指定して1レコードを削除する                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| feature_14/ut-mapper                        | スキーリゾート情報の取得、更新、削除などの単体テスト<li>すべてのスキーリゾート情報を取得する</li><li>指定したIDのスキーリゾート情報を取得する</li><li>レコードが存在しない場合に空のListを取得する</li> <li>指定したIDの情報を更新する</li><li>指定したIDの情報を削除する</li><li>更新時に指定したIDが存在しないときテーブルのレコードが更新されない</li><li>削除時に指定したIDが存在しないときテーブルのレコードが削除されない</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_16/ut-service                       | スキーリゾート情報の取得、更新、削除などサービスの単体テスト <li>存在するIDを指定した時に正常データが返されること</li><li>全てのスキーリゾート情報を取得できること</li><li>存在しないIDを指定した時にエラーメッセージが返されること</li><li>指定したIDのスキーリゾート情報を更新できること</li><li>updateSkiresortメソッド:指定したIDが存在しない時にエラーメッセージを返されること</li><li>指定したIDのスキーリゾート情報を削除できること</li><li>新規スキーリゾート情報を登録する</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_18/ut-summary                       | SkiresortMapperとSkiresortServiceImplの単体テストを順番を合わせて実行する<br>【Mapperテスト】<ol>findAll<li>全てのスキーリゾート情報を取得できること</li><li>レコードが存在しない場合に空のListが取得できること</li></ol><ol>findById<li>指定したIDのスキーリゾート情報が取得できること</li></ol><ol>insertSkiresort<li>新規のスキーリゾートを登録できること</li></ol><ol>updateSkiresort<li>指定したIDのスキーリゾート情報を更新できること</li><li>更新時に指定したIDが存在しないときテーブルのレコードが更新されないこと</li></ol><ol>deleteSkiresort<li>指定したIDのスキーリゾート情報を削除すること</li><li>削除時に指定したIDが存在しないときテーブルのレコードが削除されないこと</li></ol>【Serviceテスト】<ol>findAll<li>全てのスキーリゾート情報を取得できること</li></ol> <ol>findById<li>存在するスキーリゾートのIDを指定した時に正常にデータが返されること</li><li>存在しないIDを指定した場合findByIdメソッドはエラーメッセージを返すこと</li></ol><ol>createSkiresort<li>新規のスキーリゾートを登録できること</li></ol><ol>updateSkiresort<li>指定したIDのスキーリゾート情報を更新できること</li><li>存在しないIDを指定した場合updateSkiresortメソッドはエラーメッセージを返すこと</li></ol><ol>deleteSkiresort<li>指定したIDのスキーリゾート情報を削除すること</li></ol> |
| feature_20/gradle-test                      | GitHub Actionsでワークフローを自動化する準備段階で、`Hello World`を表示するコードを実装                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_22/githubActions-gradleTest         | `hello.yml`を単体テストを自動でビルドしてテストを行うように修正                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| feature_24/githubActions-gradleTestArtifact | Artifactを使用したGradleテストを実行                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_26/validation                       | name,area,impressionのnull,空文字,ブランクを許可しない<br>正常系テスト、以上系テストを実行する                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| feature_28/it-getAll                        | 全てのスキーリゾート情報を取得し、ステータスコード200が返されることの結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_30/it-readById                      | <li>存在しないIDを取得した時、ステータスコード404が返されることの結合テスト</li><li>存在しないIDを取得した時、ステータスコードが200が返されることの結合テスト</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| feature_32/it-create                        | スキーリゾート情報を登録する結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| feature_34/it-update                        | <li>存在するIDを指定した更新の結合テスト</li><li>存在しないIDを指定した更新の結合テスト</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_36/it-delete                        | 存在するIDを指定した削除、更新の結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| feature_39/validationImplement              | CustomExceptionHandlerの整理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_41/validator                        | nameに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| feature_43/ut-mapperTest-add                | ReadByIdメソッドのテストを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| feature_45/areaValidation                   | areaに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| feature_47/impressionValidation             | impressionに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| feature_49/ut-mapperTest-fix                | insertテストに期待値を追記                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| feature_51/correlationRequiredValidation    | <ul><li>SkiresortCreateForm:登録時のバリデーションテスト<ul><li>登録時にはname,area,impression全ての項目の入力が必要である</li></ul></li><li>SkiresortPatchForm:更新時の相関項目のチェックテスト<ul><li>更新時にはname,area,impressionの全てを入力しなくてもバリデーションエラーとならないように実装</li></ul></li></ul>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| feature_53/addComment                       | メソッドにJavaDocコメントを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| feature_55/exception-fix                    | exceptionディレクトリのCustomExceptionHandlerを2つのクラスに分割                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| feature_57/errorMessage-fix                 | エラーメッセージ`resource not found`を具体的な名前に変更<br><li>SkiresortRestApiIntegrationTest</li><li>SkiresortServiceImpl</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| feature_59/add-repositoryDirectory          | repositoryディレクトリを作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| feature_61/add-repositoryClass              | repositoryクラスを作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| feature_63/add-classComment                 | クラスコメント、JavaDocコメントを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| feature_65/organizeReadme                   | READMEを整理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| feature_67/xmlFile                          | Mapper.xmlファイルを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |

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

```
curl -X PATCH -H "Content-Type: application/json" -d '{
   "name":"nozawa-onsen",
   "area":"nagano",
   "customerEvaluation":"ゴンドラが10人乗りでガラス張りに変わった。外国人ばかりで激混み"
   }' http://localhost:8080/skiresorts/5 -i
```

- LocationHeader

```
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

```
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
