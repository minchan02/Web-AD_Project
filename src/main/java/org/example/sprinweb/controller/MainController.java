package org.example.sprinweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")          // 루트 요청
    public String home() {
        return "index";       // templates/index.html 렌더
    }
}
