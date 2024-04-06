package com.example.skiresortapi.exception;

// リソースが見つからなかった時の例外クラスを作る
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    // 指定されたメッセージと原因を持つ例外を作成する

    /**
     * 指定されたメッセージを持つ例外を作成するコンストラクタ
     *
     * @param message 例外のメッセージ
     * @param cause   この例外の原因
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // 指定されたメッセージを持つ例外を作成する

    /**
     * 指定されたメッセージを持つ例外を作成するコンストラクタ
     *
     * @param message 例外のメッセージ
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // 指定された原因を持つ例外を作成する

    /**
     * 指定された原因を持つ例外を作成するコンストラクタ
     *
     * @param cause 例外の原因
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
