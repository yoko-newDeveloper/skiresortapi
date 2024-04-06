# バリデーションパターンを整理する

### 全てのテストパターン

NGパターン

|   value    |  NG  | NG | NG  | 
|:----------:|:----:|:--:|:---:|
|    name    | null | "" | " " | 
|    area    | null | "" | " " |
| impression | null | "" | " " | 

- nameOKパターン

|   value    |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |
|:----------:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
|    name    | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge | hoge |
|    area    | null | huge | huge |  ""  | huge |  ""  | " "  | huge | " "  | null |  ""  | null | " "  |  ""  | " "  |
| impression | null | null | piyo |  ""  |  ""  | piyo | " "  | " "  | piyo |  ""  | null | " "  | null | " "  |  ""  |

- areaOKパターン

|   value    |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  | OK | OK |
|:----------:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:--:|:--:|
|    name    | null | null |  ""  |  ""  | " "  | " "  | null |  ""  | null | " "  |  ""  | " "  |      |    |    |
|    area    | huga | huga | huga | huga | huga | huga | huga | huga | huga | huga | huga | huga | huga |
| impression | null | piyo |  ""  | piyo | " "  | piyo |  ""  | null | " "  | null | " "  |  ""  |

- impressionOKパターン

|   value    |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |
|:----------:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
|    name    | null |  ""  | " "  | null |  ""  | " "  | null |  ""  | " "  |
|    area    | null |  ""  | " "  |  ""  | null | null | " "  | " "  |  ""  |
| impression | piyo | piyo | piyo | piyo | piyo | piyo | piyo | piyo | piyo |

### 実装パターン

|   value    |  NG  | NG | NG  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |
|:----------:|:----:|:--:|:---:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
|    name    | null | "" | " " | hoge | hoge |  ""  | hoge | hoge | " "  | hoge | hoge | hoge | hoge |  ""  |
|    area    | null | "" | " " | null | huga | huga |  ""  | huga | huga | " "  | huga | null | huga | " "  |
| impression | null | "" | " " | piyo | null | piyo | piyo |  ""  | piyo | piyo | " "  |  ""  | " "  | piyo |

- 論理演算子：||（論理和）
    - true || false:どちらかがtrueであれば全体がtrue
    - false || false:どちらもfalseならfalse
    - true || true || false:最初のtrueで評価終了、つまりtrue

```
// name、area、customerEvaluationがすべてnullまたは空文字列または半角スペースである場合にfalseを返す->各変数がnullまたは空文字列または半角スペースである場合に成立
if ((name == null || name.isBlank()) && (area == null || area.isBlank()) && (impression == null || impression.isBlank())) {
    return false;
    
// name、area、customerEvaluationのうち少なくとも1つが空文字列または半角スペースである場合にもfalseを返す->いずれかの変数が空文字列または半角スペースである場合に条件が成立    
} else if (name.isBlank() || area.isBlank() || impression.isBlank()) {
    return false;
}
// どちらにも当てはまらない場合にtrueを返す->全ての変数がnull,空文字,半角スペースではない場合
return true;
```

```
.extracting(
    violation -> violation.getPropertyPath().toString(), // プロパティのパスを文字列に変換
    ConstraintViolation::getMessage // 制約違反のメッセージを取得
)
.containsExactlyInAnyOrder(
    tuple(
        "nameOrAreaOrImpression", // 期待されるプロパティのパス
        "name, area, impressionのいずれかを入力してください" // 期待されるエラーメッセージ
    )
);
```

## SkiresortPatchFormTestの目的

- データ更新時にname,area,impressionの全てを入力しなくて良いために作成
- SkiresortPatchFormクラスを新規作成し、相関項目チェックできるように実装
