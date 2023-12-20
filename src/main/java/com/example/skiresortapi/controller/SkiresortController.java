package com.example.skiresortapi.controller;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import com.example.skiresortapi.controller.form.SkiresortUpdateForm;
import com.example.skiresortapi.controller.response.SkiresortResponse;
import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.exception.ResourceNotFoundException;
import com.example.skiresortapi.service.SkiresortService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class SkiresortController {
    private final SkiresortService skiresortService;

    public SkiresortController(SkiresortService skiresortService) {
        this.skiresortService = skiresortService;
    }

    @GetMapping("/skiresorts")
    public List<SkiresortResponse> skiresorts() {
        List<Skiresort> skiresorts = skiresortService.findAll();
        List<SkiresortResponse> skiresortResponses = skiresorts.stream().map(SkiresortResponse::new).toList();
        return skiresortResponses;
    }

    @GetMapping("/skiresorts/{id}")
    public SkiresortResponse getSkiresortById(@PathVariable("id") int id) {
        Skiresort skiresort = skiresortService.findById(id);
        return new SkiresortResponse(skiresort);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> noResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {

        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());

        // 404エラーを返す
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/skiresorts")
    public ResponseEntity<SkiresortResponse> createSkiresort(@RequestBody SkiresortCreateForm skiresortCreateForm, HttpServletRequest request) {
        Skiresort skiresort = skiresortService.createSkiresort(skiresortCreateForm);

        // skiresortオブジェクトを元にレスポンス用のオブジェクトを生成
        SkiresortResponse skiresortResponse = new SkiresortResponse(skiresort);
        // HttpServletRequestのインスタンスでリクエストの中身を取得し、動的なURLを生成してハードコードを避ける
        URI url = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .path("/skiresorts/{id}")
                .buildAndExpand(skiresort.getId())
                .toUri();
        return ResponseEntity.created(url).body(skiresortResponse);
    }

    @PatchMapping("/skiresorts/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable("id") int id, @RequestBody @Valid SkiresortUpdateForm form) {

        // id以外のSkiresortUpdateFormの情報を使用してレコードを更新する
        skiresortService.updateSkiresort(id, form.getName(), form.getArea(), form.getImpression());
        return ResponseEntity.ok(Map.of("message", "successfully update"));
    }
}
