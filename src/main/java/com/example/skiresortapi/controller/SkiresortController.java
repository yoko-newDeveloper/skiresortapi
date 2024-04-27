package com.example.skiresortapi.controller;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import com.example.skiresortapi.controller.form.SkiresortPatchForm;
import com.example.skiresortapi.controller.response.SkiresortResponse;
import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.service.SkiresortService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * スキーリゾートに関する情報を提供するController
 */
@RestController
public class SkiresortController {
    private final SkiresortService skiresortService;

    /**
     * コンストラクタ
     *
     * @param skiresortService スキーリゾートServiceクラス
     */
    public SkiresortController(SkiresortService skiresortService) {
        this.skiresortService = skiresortService;
    }

    /**
     * 全てのスキーリゾート情報を取得
     *
     * @return スキーリゾート情報リスト
     */
    @GetMapping("/skiresorts")
    public List<SkiresortResponse> skiresorts() {
        List<Skiresort> skiresorts = skiresortService.findAll();
        List<SkiresortResponse> skiresortResponses = skiresorts.stream().map(SkiresortResponse::new).toList();
        return skiresortResponses;
    }

    /**
     * 指定IDのスキーリゾート情報を取得する
     *
     * @param id 取得するスキーリゾート情報のID
     * @return スキーリゾート情報
     */
    @GetMapping("/skiresorts/{id}")
    public SkiresortResponse getSkiresortById(@PathVariable("id") int id) {
        Skiresort skiresort = skiresortService.findById(id);
        return new SkiresortResponse(skiresort);
    }

    /**
     * 新しいスキーリゾートの登録
     *
     * @param skiresortCreateForm 登録するスキーリゾート情報のフォーム
     * @param uriBuilder          新規スキーリゾートに対応するURLを生成する
     * @return 新規スキーリゾート情報のレスポンス
     */
    @PostMapping("/skiresorts")
    public ResponseEntity<?> createSkiresort(@RequestBody @Valid SkiresortCreateForm skiresortCreateForm, UriComponentsBuilder uriBuilder) {
        Skiresort skiresort = skiresortService.createSkiresort(skiresortCreateForm);

        // skiresortオブジェクトを元にレスポンス用のオブジェクトを生成
        SkiresortResponse skiresortResponse = new SkiresortResponse(skiresort);
        // URI:リソースの位置や名前を特定する
        // HttpServletRequestのインスタンスでリクエストの中身を取得し、動的なURLを生成
        URI location = uriBuilder.path("/skiresorts/{id}").buildAndExpand(skiresort.getId()).toUri();
        return ResponseEntity.created(location).body(skiresortResponse);
    }

    /**
     * スキーリゾートの更新
     *
     * @param id   更新するスキーリゾートのID
     * @param form 更新するスキーリゾート情報のフォーム
     * @return 更新完了メッセージ:"successfully update"
     */
    @PatchMapping("/skiresorts/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable("id") int id, @RequestBody @Valid SkiresortPatchForm form) {
        // id以外のSkiresortUpdateFormの情報を使用してレコードを更新する
        skiresortService.updateSkiresort(id, form.getName(), form.getArea(), form.getImpression());
        return ResponseEntity.ok(Map.of("message", "successfully update"));
    }

    /**
     * 指定したIDのスキーリゾート情報を削除
     *
     * @param id 削除するスキーリゾートのID
     * @return 削除完了メッセージ:successfully deleted
     */

    @DeleteMapping("/skiresorts/{id}")
    public ResponseEntity<Map<String, String>> deleteSkiresort(@PathVariable("id") int id) {
        skiresortService.deleteSkiresort(id);
        return ResponseEntity.ok(Map.of("message", "successfully deleted"));
    }
}
