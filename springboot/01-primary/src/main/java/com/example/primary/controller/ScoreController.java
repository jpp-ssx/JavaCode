package com.example.primary.controller;

import com.example.primary.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    IScoreService iScoreService;

    @GetMapping("/addScore")
    public void addScore() {
        iScoreService.insertScore();
    }

}
