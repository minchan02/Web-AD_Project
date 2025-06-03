package org.example.sprinweb.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.sprinweb.board.*;
import org.springframework.web.bind.annotation.*;
import org.example.sprinweb.board.exception.BoardNotFoundException;

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
        List<Board> list = repo.findByWriterName(username);
        if (list.isEmpty()) {
            throw new BoardNotFoundException("작성자 '" + username + "' 의 글이 없습니다.");
        }
        return list;
    }
}
