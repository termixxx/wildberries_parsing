package com.example.wb_api_parising.controller;

import com.example.wb_api_parising.service.ParsingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/parsing")
public class ParsingController {
    private final ParsingService parsingHtmlImpl;
    private final ParsingService parsingApiImpl;


    public ParsingController(@Qualifier("ParsingHtmlImpl") ParsingService parsingHtmlImpl,
                             @Qualifier("ParsingApiImpl") ParsingService parsingApiImpl) {
        this.parsingHtmlImpl = parsingHtmlImpl;
        this.parsingApiImpl = parsingApiImpl;
    }

    @PostMapping("/api/brands/{brandId}")
    public void parsingApi(@PathVariable Long brandId) {
        parsingApiImpl.parse(brandId);
    }

    @PostMapping("/html/brands/{brandId}")
    public void parsingHtml(@PathVariable Long brandId) {
        parsingHtmlImpl.parse(brandId);
    }

}
