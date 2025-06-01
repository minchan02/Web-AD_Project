package org.example.sprinweb.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.RequiredArgsConstructor;
import org.example.sprinweb.board.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;
    private final BoardRepository repo;

    @GetMapping
    public String list(Model model){
        return "board/list";
    }

    @GetMapping("/new")
    public String form() { return "board/new"; }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @RequestParam MultipartFile file) throws IOException {
        service.save(title, content, file);
        return "redirect:/board";
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Board b = repo.findById(id).orElseThrow();
        Path p = Paths.get(System.getProperty("user.dir"))
                .resolve("uploads")
                .resolve(b.getStoredName());

        Resource resource = new UrlResource(p.toUri());
        String filename = URLEncoder.encode(b.getOriginalName(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
