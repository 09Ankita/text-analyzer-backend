package com.dedalus.textanalyzer.controller;

import com.dedalus.textanalyzer.model.Text;
import com.dedalus.textanalyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    @Autowired
    public TextAnalyzerController(TextAnalyzerService textAnalyzerService) {
        this.textAnalyzerService = textAnalyzerService;
    }

    /**
     * REST API to return the Ok status.
     */
    @GetMapping("")
    public ResponseEntity<?> getStatus() {
        //return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok("Online");
    }

    /**
     * REST API to analyze the text.
     * @param text Text model request body containing type = 'vowels' or 'consonants' and text
     */
    @PostMapping("/analyze")
    public HashMap<Character, Integer> analyzeText(@RequestBody Text text) {
        return textAnalyzerService.analyze(text);
    }
}
