# バリデーションエラー

- Controllerのレイヤーで実装する
- `@NotNull`、`@NotBlank`、`@Size`、`@Min`、`@Email`など
- HTTPステータスコード400（Bad Request）を返す
- `@RestControllerAdvice`を使用してグローバルなエラーハンドラを実装する
    - `@RestControllerAdvice`を使用してアプリケーション全体で発生するエラーをキャッチして処理するための、グローバルなエラーハンドリングを行うことが適切である
    - `CustomExceptionHandler`は、@RestControllerAdviceを使用して実装した例外ハンドラである
- MethodArgumentNotValidException をキャッチしてバリデーションエラーを処理する

    - SpringBootでは`MethodArgumentNotValidExceptiton`がスローされた際に実行する
    - コントローラメソッド（Controllerクラスの@Validが付いているメソッドの引数）が不正な場合に発生する
    - --> バリデーション情報を取得し、エラーレスポンスを返す
    - BindingResultを使用しない場合、`MethodArgumentNotValidException`メソッドを使用する

- 気をつけること
- `@Valid`
    - Controllerの引数で使用
    - `@RequestBody`と共に引数の直前に記述し、引数のフィールドに対して、リクエストボディのバリデーションを行う
    - 主にJava標準のバリデーション（jakarta.validationパッケージ）を実行するために使用

- `@Validated`
    - ServiceやControllerクラスのメソッド内の引数）。Spring特有のバリデーションを実行するために使用
    - Java Bean Validationの場合に使用する
    - HTTPステータスコード404（Not Found）を返す

# 特定の例外

- Javaのカスタム例外であり、特定の条件やエラーが発生した際に定義する
- `ResourceNotFoundException`などに対してカスタムなエラーハンドリングを行う
- `@ExceptionHandler`を使用して、個別の例外クラスに対するエラーハンドリングを実装する

    - Controllerクラスのメソッド内でリソースが見つからないなど
    - --> カスタムな例外をスローする（ResourceNotFoundExceptionなど）
    - --> 例外をキャッチし、適切なレスポンスを生成する
    - --> HTTPステータスコード404（Not Found）を返す

- 例外がスローするまで、例外ハンドリングが行われない

# バリデーションの目的

フロント側ですり抜けたエラーをバックエンドで拾うことがある->Javaでのバリデーションは必要

- 入力内容や記述内容が要件を満たしているか、妥当性を確認すること
- 001-create-table-and-load-data.sqlで定義されているバリデーションよりも優先的にエラーチェックをする(Mapperの処理より先にエラーチェックを行う)

|           | Null | 空文字 | 空白 | 全角スペース |
|:---------:|:----:|:---:|:--:|:------:| 
| @NotNull  |  ×   |  ◯  | ◯  |   ◯    |
| @NotEmpty |  ×   |  ×  | ◯  |   ◯    |
| ＠NotBlanc |  ×   |  ×  | ×  |   ◯    |

- `@Not Null`:変数やメソッドの引数がnullであってはならない。変数や引数には必ず値が入っていなければならない。
- `@Not Blanc`:文字列が空ではないこと。文字列を少なくとも1つ以上持っていること(名前が空白のままでは送信できないなど)
- `@Not Empty`：コレクション（リスト、セットなど）が空ではないこと。1つ以上の要素を持っていること。
- 空文字:文字列が0のこと。
- null:箱も中身もない。何も存在しない。
- blanc:箱はあるけど何も入っていない。半角スペース、全角スペースのこと。

`@Validated`:`@Valid`を拡張したSpringの機能
`@Validated`:`@RequestParam`アノテーションを使用する場合
`@Valid`:Jakarta Bean Validationで定義される

## 仮説を立ててデバッグ実行

`@ExceptionHandler`で通るか？(問題がなければ通らない->問題が0個なら通らない)<br>
`MethodArgumentNotValidExceptjon`:Controllerに@Validや@ValidatedをつけたuserPostrequestをentityでバリデーションした(@NotBlankなど)結果<br>
->Not Valid(正しくない)例外になるハンドラーである<br>
->postリクエストを送ると返ってくる(返ってくる＝失敗)<br>
->正しい場合:例外は出ない<br>
->ExceptionHandlerを通る場合またはControllerを通る場合に分かれる
