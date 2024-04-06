# GlobalExceptionHandler

アプリケーション全体で発生する例外を処理するグローバルな例外ハンドラ

- `@RestControllerAdvice`アノテーション:全ての`@Controller`クラスで発生する例外を処理する
- `handleMethodArgumentNotValid`メソッド:MethodArgumentNotValidExceptionがスローされた時に呼び出され、メソッドの引数がバリデーションに失敗した時の処理を行う
  <br>->バリデーションエラーのフィールドとメッセージを取得し、400エラーレスポンスを返す

# ResourceNotFoundExceptionHandler

`ResourceNotFoundException`が発生した時、適切なHTTPレスポンスを生成するための例外ハンドラ

- 特定の種類の例外に対するエラーハンドラ
- リソースが見つからない場合のエラー処理を行う
- `@ExcptionHandler`:ResourceNotFoundExceptionがスローされた時、noResourceFoundメソッドが呼び出される
- noResourceFound:リクエストされたリソースが見つからない場合のエラーレスポンスを作成
  ->timestamp,HTTPステータスコード,エラーメッセージ,リクエストURIなどの情報を含むレスポンス本文作成
  ->ResponseEntityでラップして返す
