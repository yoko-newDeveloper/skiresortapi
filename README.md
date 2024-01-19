# skiresortapiアプリケーション概要

スキー場における名称・エリア・特徴についてのCRUDアプリケーション及びテスト
テストレポートは自動生成する

# 存在しないidを指定した場合404エラーを返す

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
