# 利用するHTTPメソッド

GET、POST、PUT、DELETE

- PUT:リソースの更新を行う
- PATCH:リソースの更新を行わない

## @PostMapping

- 新しいスキーリゾートを生成してそれに対応するURLを生成して返す
- `ResponseEntity<SkiresortResponse>`:SkiresortResponseという情報を持ったHTTPステータスコードが201(Created)のResponseEntityを返す
- `createSkiresort`:このメソッドが呼ばれる時、新しいスキーリゾートが生成される
- `@RequestBody SkiresortCreateForm skiresortCreateForm`:HTTPリクエストの中にあるSkiresortCreateForm(データ)を受け取る
- `HttpServletRequest request`:このメソッドが呼ばれた際のHTTPリクエストを持っている
- `@Valid`:バリデーションを行い、フォームデータが正しくない場合はエラーメッセージが生成される
- `Skiresort`のフィールドに合わせてJSONデータを定義しないとエラーになる

## Locationヘッダの値設定のためにUriComponenBuilderを使う

UriComponentsBuilder：URLを構築するためのクラス

Locationヘッダの値：新しいURIである必要がある

登録APIの場合：新規に作成されたリソースのアクセス先となるURLを設定する

新しいスキーリゾートを作成する際に`UriComponentsBuilder`を使って新しいスキーリゾートのURIを作成 ->`ResponseEntity.created()`
によってLocationヘッダーにセットする ->新しく作成されたスキーリゾートのURLを取得する

uriBuilderに対してpath`"/skiresorts/{id}"`を設定する buildAndExpand:`skiresort.getId()を挿入する ResponseEntity.created(url):
Locationヘッダが含まれ、その値としてurlが指定されている

### curlコマンド

```agsl
$ curl -i -X POST -H "Content-Type: application/json" -d '{
  "id": 1,
  "name": "Skiresort Name",
  "area": "Skiresort Area",
  "customerEvaluation": "Skiresort Customer Evaluation"
}' http://localhost:8080/skiresorts
```

`Skiresort`:id,name,area,impressionフィールドが4つある

`SkiresortResponse`name,areaフィールドにあるレスポンスを返す(返したい情報を絞っている)
