package com.example.skiresortapi.controller;

import com.example.skiresortapi.controller.response.SkiresortResponse;
import com.example.skiresortapi.entity.Skiresort;
import com.example.skiresortapi.service.SkiresortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
