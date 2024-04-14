package com.example.skiresortapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Map;

// アプリケーション全体で発生するエラーをキャッチして処理するグローバルなエラーハンドリング
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * メソッド引数が妥当出ない場合のエラーハンドリング
     *
     * @param ex      MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status  Httpステータスコード
     * @param request Webリクエスト
     * @return バリデーションエラーのレスポンス
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        MultiValueMap<String, String> invalidParam = new LinkedMultiValueMap<>();
        for (FieldError e : ex.getFieldErrors()) {
            invalidParam.add(e.getField(), e.getDefaultMessage());
        }

        Map<String, Object> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", invalidParam);

        // 400エラーを返す
        return ResponseEntity.badRequest().body(body);
    }
}
