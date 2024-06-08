# スキーリゾートアプリケーション

## 概要

このアプリケーションは、スキーリゾートの情報を管理するためのCRUDアプリケーションです。<br>
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

---

## 使用技術

- Java
- SpringBoot
- MyBatis
- MySQL
- Docker
- CI(GitHub Actions)
- 自動テスト

---

## 目次

1. [データベース周りの作成](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_%231/database)
2. [readとcreateの実装](https://github.com/yoko-newDeveloper/skiresortapi/pull/4)
2. [エラーハンドリング実装](https://github.com/yoko-newDeveloper/skiresortapi/pull/6)
3. [存在しないIDを指定して取得する例外処理](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_%235/errorHandling)
4. [仮データでCreateしたID5を更新する/Createのハードコードを回避するように修正](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_7/update)
5. [nullを許可しないカスタムなバリデーション](https://github.com/yoko-newDeveloper/skiresortapi/pull/11)
6. [IDを指定して1レコードを削除する](https://github.com/yoko-newDeveloper/skiresortapi/pull/13)
7. [スキーリゾート情報の取得、更新、削除などの単体テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/15)
8. [スキーリゾート情報の取得、更新、削除などサービスの単体テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/17)
9. [SkiresortMapperとSkiresortServiceImplの単体テストを順番に実行する](https://github.com/yoko-newDeveloper/skiresortapi/pull/19)
10. [GitHub Actionsでワークフローを自動化する準備段階で、`Hello World`を表示するコードを実装](#github-actionsでワークフローを自動化する準備段階でhello-worldを表示するコードを実装)
11. [単体テストを自動でビルドしてテストを行うようにGradleを設定](https://github.com/yoko-newDeveloper/skiresortapi/pull/23)
12. [Gradleを使用した単体テストをArtifactを使用して実行](https://github.com/yoko-newDeveloper/skiresortapi/pull/25)
13. [name,area,impressionのバリデーションテスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/27)
14. [全てのスキーリゾート情報を取得し、ステータスコード200が返されることの結合テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/29)
15. [存在するID、存在しないIDを指定した取得のレスポンスを確認する結合テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/31)
16. [スキーリゾート情報を登録する結合テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/33)
17. [存在するID、存在しないIDを指定した更新の結合テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/35)
18. [存在するIDを指定した削除の結合テスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/38)
19. [CustomExceptionHandlerの整理](https://github.com/yoko-newDeveloper/skiresortapi/pull/40)
20. [nameに関するバリデーション](https://github.com/yoko-newDeveloper/skiresortapi/pull/42)
21. [ID指定してスキーリゾート情報新規登録のテストを追加](https://github.com/yoko-newDeveloper/skiresortapi/pull/44)
22. [areaに関するバリデーション](https://github.com/yoko-newDeveloper/skiresortapi/pull/46)
23. [impressionに関するバリデーション](https://github.com/yoko-newDeveloper/skiresortapi/pull/48)
24. [insertテストに期待値を追記](https://github.com/yoko-newDeveloper/skiresortapi/pull/50)
25. [更新時の相関項目のチェックテスト](https://github.com/yoko-newDeveloper/skiresortapi/pull/52)
26. [メソッドにJavaDocコメントを追加](https://github.com/yoko-newDeveloper/skiresortapi/pull/54)
27. [エラーメッセージを具体的な名前に変更](https://github.com/yoko-newDeveloper/skiresortapi/pull/58)
28. [exceptionディレクトリのCustomExceptionHandlerを2つのクラスに分割](https://github.com/yoko-newDeveloper/skiresortapi/pull/56)
29. [repositoryディレクトリを作成](https://github.com/yoko-newDeveloper/skiresortapi/pull/60)
30. [repositoryクラスを作成](https://github.com/yoko-newDeveloper/skiresortapi/pull/62)
31. [クラスコメント、JavaDocコメントを追加](https://github.com/yoko-newDeveloper/skiresortapi/pull/64)
32. [READMEを整理](https://github.com/yoko-newDeveloper/skiresortapi/pull/66)
33. [XMLファイルを追加](https://github.com/yoko-newDeveloper/skiresortapi/pull/68)
34. [READMEに設計書を追加](https://github.com/yoko-newDeveloper/skiresortapi/pull/70)
35. [Entityを修正](https://github.com/yoko-newDeveloper/skiresortapi/pull/72)

---

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
	│   ├── Area
	│   ├── Impression
	│   └── Skiresort
	├── exception
	│   ├── CustomExceptionHandler
	│   └── ResourceNotFoundException
	│── mapper
	│   └── SkiresortMapper
	├── repository
	│   ├── AreaRepository
	│   ├── ImpressionRepository
	│   └── SkiresortRepository
	├── service
	│   ├── AreaService
	│   ├── AreaServiceImpl
	│   ├── ImpressionService
	│   ├── ImpressionServiceImpl
	│   ├── SkiresortService
	│   └── SkiresortServiceImpl
	└── scr/main/resources
	    └── com/examplt/skiresortapi/mapper/SkiresortMapper.xml
       
 
```

---

## 設計書

### クラス図

![453F74DC-CE58-4EE1-8050-AF2C8EB74298_1_201_a.jpeg](..%2F..%2FPictures%2F%E5%86%99%E7%9C%9F%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA.photoslibrary%2Fresources%2Frenders%2F4%2F453F74DC-CE58-4EE1-8050-AF2C8EB74298_1_201_a.jpeg)

### シーケンス図

![2BEC7F95-38F0-45C4-8A2C-9D6B75DA8B99_1_201_a.jpeg](..%2F..%2FPictures%2F%E5%86%99%E7%9C%9F%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA.photoslibrary%2Fresources%2Frenders%2F2%2F2BEC7F95-38F0-45C4-8A2C-9D6B75DA8B99_1_201_a.jpeg)

### E-R図

![FB08205D-E2EC-48DA-9ECC-C7B73884151F_1_201_a.jpeg](..%2F..%2FPictures%2F%E5%86%99%E7%9C%9F%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA.photoslibrary%2Fresources%2Frenders%2FF%2FFB08205D-E2EC-48DA-9ECC-C7B73884151F_1_201_a.jpeg)

### テーブル定義書

![7BCF43AE-F3C1-437F-9075-CE18CD97C13A.jpeg](..%2F..%2FPictures%2F%E5%86%99%E7%9C%9F%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA.photoslibrary%2Foriginals%2F7%2F7BCF43AE-F3C1-437F-9075-CE18CD97C13A.jpeg)

---

## 実装内容

| ブランチ名                                                                                                                                             | 機能                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [feature_1/database](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_%231/database)                                                | データベース周りの作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [feature_3/read](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_%233/read)                                                        | skiresort テーブルのデータ取得<li>Read:skiresortテーブルの前データを取得する</li><li>指定したIDのスキーリゾート情報を取得する</li><li>Create:新規ID5の仮データを作成</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [feature_5/errorHandling](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_%235/errorHandling)                                      | 存在しない ID を指定して取得する例外処理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [feature_7/update](https://github.com/yoko-newDeveloper/skiresortapi/pull/8)                                                                      | 仮データでCreateしたID5を更新する/Createのハードコードを回避するように修正                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [feature_7/update-fix](https://github.com/yoko-newDeveloper/skiresortapi/pull/10)                                                                 | SkiresortMapperのtypo修正                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [feature_9/exception](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_9/exception)                                                 | ID5のname,impressionデータを更新                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_11/delete](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_11/delete)                                                     | ID5のname,impressionデータを更新                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_14/delete](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_14/ut-mapper)                                                  | IDを指定して1レコードを削除する                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [feature_16/ut-service](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_16/ut-service)                                             | スキーリゾート情報の取得、更新、削除などの単体テスト<li>すべてのスキーリゾート情報を取得する</li><li>指定したIDのスキーリゾート情報を取得する</li><li>レコードが存在しない場合に空のListを取得する</li> <li>指定したIDの情報を更新する</li><li>指定したIDの情報を削除する</li><li>更新時に指定したIDが存在しないときテーブルのレコードが更新されない</li><li>削除時に指定したIDが存在しないときテーブルのレコードが削除されない</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_18/ut-summary](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_18/ut-summary)                                             | スキーリゾート情報の取得、更新、削除などサービスの単体テスト <li>存在するIDを指定した時に正常データが返されること</li><li>全てのスキーリゾート情報を取得できること</li><li>存在しないIDを指定した時にエラーメッセージが返されること</li><li>指定したIDのスキーリゾート情報を更新できること</li><li>updateSkiresortメソッド:指定したIDが存在しない時にエラーメッセージを返されること</li><li>指定したIDのスキーリゾート情報を削除できること</li><li>新規スキーリゾート情報を登録する</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_20/gradle-test](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_20/gradle-test)                                           | SkiresortMapperとSkiresortServiceImplの単体テストを順番を合わせて実行する<br>【Mapperテスト】<ol>findAll<li>全てのスキーリゾート情報を取得できること</li><li>レコードが存在しない場合に空のListが取得できること</li></ol><ol>findById<li>指定したIDのスキーリゾート情報が取得できること</li></ol><ol>insertSkiresort<li>新規のスキーリゾートを登録できること</li></ol><ol>updateSkiresort<li>指定したIDのスキーリゾート情報を更新できること</li><li>更新時に指定したIDが存在しないときテーブルのレコードが更新されないこと</li></ol><ol>deleteSkiresort<li>指定したIDのスキーリゾート情報を削除すること</li><li>削除時に指定したIDが存在しないときテーブルのレコードが削除されないこと</li></ol>【Serviceテスト】<ol>findAll<li>全てのスキーリゾート情報を取得できること</li></ol> <ol>findById<li>存在するスキーリゾートのIDを指定した時に正常にデータが返されること</li><li>存在しないIDを指定した場合findByIdメソッドはエラーメッセージを返すこと</li></ol><ol>createSkiresort<li>新規のスキーリゾートを登録できること</li></ol><ol>updateSkiresort<li>指定したIDのスキーリゾート情報を更新できること</li><li>存在しないIDを指定した場合updateSkiresortメソッドはエラーメッセージを返すこと</li></ol><ol>deleteSkiresort<li>指定したIDのスキーリゾート情報を削除すること</li></ol> |
| [feature_22/githubActions-gradleTest](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_22/githubActions-gradleTest)                 | GitHub Actionsでワークフローを自動化する準備段階で、`Hello World`を表示するコードを実装                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_24/githubActions-gradleTestArtifact](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_24/githubActions-gradleTestArtifact) | `hello.yml`を単体テストを自動でビルドしてテストを行うように修正                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| [feature_26/validation ](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_26/validation)                                            | Artifactを使用したGradleテストを実行                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_28/it-getAll](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_28/it-getAll)                                               | name,area,impressionのnull,空文字,ブランクを許可しない<br>正常系テスト、以上系テストを実行する                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| [feature_30/it-readById](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_30/it-readById)                                           | 全てのスキーリゾート情報を取得し、ステータスコード200が返されることの結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_32/it-create](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_32/it-create)                                               | <li>存在しないIDを取得した時、ステータスコード404が返されることの結合テスト</li><li>存在しないIDを取得した時、ステータスコードが200が返されることの結合テスト</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [feature_34/it-update](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_34/it-update)                                               | スキーリゾート情報を登録する結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [feature_36/it-delete](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_36/it-delete)                                               | <li>存在するIDを指定した更新の結合テスト</li><li>存在しないIDを指定した更新の結合テスト</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_39/validationImplement](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_39/validationImplement)                           | 存在するIDを指定した削除、更新の結合テスト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [feature_41/validator](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_41/validator)                                               | CustomExceptionHandlerの整理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_43/ut-mapperTest-add](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_43/ut-mapperTest-add)                               | nameに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [feature_45/areaValidation](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_45/areaValidation)                                     | ReadByIdメソッドのテストを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [feature_47/impressionValidation](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_47/imperssionValidation)                         | areaに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [feature_49/ut-mapperTest-fix](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_49/ut-mapperTest-fix)                               | impressionに関するバリデーション                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| [feature_51/correlationRequiredValidation](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_51/correlationRequiredValidation)       | insertテストに期待値を追記                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [feature_53/addComment](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_53/addComment)                                             | <ul><li>SkiresortCreateForm:登録時のバリデーションテスト<ul><li>登録時にはname,area,impression全ての項目の入力が必要である</li></ul></li><li>SkiresortPatchForm:更新時の相関項目のチェックテスト<ul><li>更新時にはname,area,impressionの全てを入力しなくてもバリデーションエラーとならないように実装</li></ul></li></ul>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [feature_55/exception-fix](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_55/eception-fix)                                        | メソッドにJavaDocコメントを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [feature_57/errorMessage-fix](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_57/errorMessage-fix)                                 | exceptionディレクトリのCustomExceptionHandlerを2つのクラスに分割                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [feature_59/add-repositoryDirectory](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_59/add-repositoryDirectory)                   | エラーメッセージ`resource not found`を具体的な名前に変更<br><li>SkiresortRestApiIntegrationTest</li><li>SkiresortServiceImpl</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [feature_61/add-repositoryClass](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_61/add-repositoryClass)                           | repositoryディレクトリを作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [feature_63/add-classComment](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_63/add-classComment)                                 | repositoryクラスを作成                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [feature_65/organizeReadme](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_65/organizeReadme)                                     | クラスコメント、JavaDocコメントを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [feature_67/xmlFile](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_67/xmlFile)                                                   | READMEを整理                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [feature_69/design](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_69/design)                                                     | Mapper.xmlファイルを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [feature_71/addEntity](https://github.com/yoko-newDeveloper/skiresortapi/tree/feature_71/addEntity)                                               | READMEに設計書を追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [feature_73/entity-fix]()                                                                                                                         | Entityを追加                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| []()                                                                                                                                              | entityの修正                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |

---

## 実装順理由

| 順番 | 機能                 | 理由                                                  |
|:--:|--------------------|-----------------------------------------------------|
| 1  | Read               | テーブルの全てのデータを取得してから開始するため                            |
| 2  | Read(id)           | idに関するErrorHandlingを実装するため                          |
| 3  | idのErrorHandling   | アプリケーションの安定性と信頼性を向上させるために重要な要素であり、早い段階で実装するべきと考えたため |
| 4  | Update             | Createした仮データを完成させるため                                |
| 5  | nameのErrorHandling | 早い段階で実装すべきだが、Update作成後にテストする必要があるため                 |
| 6  | Delete             | 実装予定機能が完了してからDeleteするため                             |

---

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

---

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

---

## 動作確認ポイント

| 項目           | レスポンス                |
|--------------|----------------------|
| HTTP         | 1.1 404              |
| Content-Type | application/json     |
| error        | Not Found"           |
| message      | "resource not found" |
| status       | "404"                |
| path         | "99"/"55"            |

---

## 動作確認キャプチャ

![2C477B19-77AE-481D-96CC-716618B99999_1_201_a](https://github.com/yoko-newDeveloper/raiseTech-course-task10/assets/91002836/172039f9-cf55-4381-8d2e-68cee9890da7)
