# skiresortapiアプリケーション概要

スキー場における名称・エリア・特徴についてのCRUDアプリケーション及びテスト
テストレポートは自動生成する

# 存在しないidを指定した場合404エラーを返す

## 実装内容

ブランチ：feature_#1/database

| 機能       | 説明            |
|----------|---------------|
| database | database周りを作成 |

ブランチ：feature_#３/read

| 機能       | 説明                      |
|----------|-------------------------|
| Read     | skiresortテーブルの全データを取得する |
| Read(id) | 指定した既存idのデータを取得する       |
| Create   | 新規id5を作成(仮データ)          |

ブランチ：feature_#5/errorHandling

| 機能            | 説明                   |
|---------------|----------------------|
| ErrorHandling | 存在しないidを指定して取得する例外処理 |

ブランチ：feature_#7/update

| 機能     | 説明                         |
|--------|----------------------------|
| Update | ID5のname,impressionデータ更新する |
|
| Delete | IDを指定して1レコードを削除する          |

ブランチ：feature_#9/exception

| 機能            | 説明         |
|---------------|------------|
| ErrorHandling | nameに対する制御 |

## 実装順理由

| 順番 | 機能                 | 理由                                                  |
|:--:|--------------------|-----------------------------------------------------|
| 1  | Read               | テーブルの全てのデータを取得してから開始するため                            |
| 2  | Read(id)           | idに関するErrorHandlingを実装するため                          |
| 3  | idのErrorHandling   | アプリケーションの安定性と信頼性を向上させるために重要な要素であり、早い段階で実装するべきと考えたため |
|    |                    |
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

## 動作確認キャプチャ

![2C477B19-77AE-481D-96CC-716618B99999_1_201_a](https://github.com/yoko-newDeveloper/raiseTech-course-task10/assets/91002836/172039f9-cf55-4381-8d2e-68cee9890da7)
