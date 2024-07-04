# Repositoryクラスを使用せずデータベース操作を行う

## EntityManager

Serviceクラスでデータベース操作を行う

## SkiresortServiceImpl

## テーブル追加前

```    
    @Override
    public List<Skiresort> findAll() {
        return skiresortMapper.findAll();
    }
```

- `skiresortMapper.findAll()`を呼び出してリストを返していた

## テーブル追加後

- `Area`や`impression`という新しい情報を扱うため、`Skiresrt`エンティティ自体に`Area`や`Impression`の情報を持たせる必要がある
- `Skiresort`エンティティクラスに`Area`や`Impression`を参照するためのフィールドを追加->`findAll`メソッドで取得するリストは`Skiresort`のリストになった

### sqlの修正

- area_idはareaテーブルの主キーに対応している
- impression_idはimpressionテーブルの主キーに対応している
- 外部キー制約(FOREIGN KEY)により、skiresortテーブルのarea_idとimpression_idがareaテーブルとimpressionテーブルの存在する主キーと一致することが保証される

### Mapperの修正

- SkiresortMapper(既存):全てのスキーリゾート情報の取得、ID指定のスキーリゾート情報の取得
- AreaMapper:ID指定のarea情報の取得
- ImpressionMapper:ID指定のimpression情報の取得

## Entityの修正

- Area、Impressionを定義し、Skiresortに関連づける
- `@ManyToOne`:スキーリゾートが1つのareaと1つのimpressionに関連していることを示す
- `@JoinColumn`:それぞれのフィールドがデータベースのどのカラムに対応しているかを示す
