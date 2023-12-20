# skiresortapiアプリケーション概要

スキー場における名称・エリア・特徴についてのCRUDアプリケーション及びテスト
テストレポートは自動生成する

## 存在しないidを指定した場合404エラーを返す

## 実装内容

| ブランチ名                    | 機能            | 説明                                    |
|--------------------------|---------------|---------------------------------------|
| feature_#1/database      | database      | database周りを作成                         |
| feature_#3/read          | Read          | skiresortテーブルの全データを取得する               |
|                          | Read(id)      | 指定した既存idのデータを取得する                     |
|                          | Create        | 新規id5を作成(仮データ)                        |
| feature_#5/errorHandling | ErrorHandling | 存在しないidを指定して取得する例外処理                  |
| feature_#7/update        | Update        | 存在するID/存在しないIDのname,impressionデータ更新する |
| feature_9/exception      | ErrorHandling | nameに対する制御                            |

ブランチ：feature_#7/update

| 機能     | 説明                         |
|--------|----------------------------|
| Update | ID5のname,impressionデータ更新する |
|
| Delete | IDを指定して1レコードを削除する          |

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

### 例外確認

- 存在しないID99を指定

`curl http://localhost:8080/skiresorts/99 -i`

- 存在しないID55を指定

`curl http://localhost:8080/skiresorts/55 -i`

## 動作確認ポイント

| 項目           | レスポンス                |
|--------------|----------------------|
| HTTP         | 1.1 404              |
| Content-Type | application/json     |
| error        | Not Found"           |
| message      | "resource not found" |
| status       | "404"                |
| path         | "99"/"55"            |
