// src/main/java/org/example/sprinweb/controller/BoardApiController.java
package org.example.sprinweb.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.sprinweb.board.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository repo;

    @GetMapping("/api/list")
    public List<Board> list() {
        return repo.findAll();
    }

    @PostMapping("/api/find")
    public List<Board> find(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        return repo.findByWriterName(username);
    }
}
