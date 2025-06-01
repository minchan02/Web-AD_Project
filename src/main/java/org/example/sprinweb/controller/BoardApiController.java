package org.example.sprinweb.controller;

import lombok.RequiredArgsConstructor;
import org.example.sprinweb.board.Board;
import org.example.sprinweb.board.BoardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController          // JSON 전용
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository repo;

    @GetMapping("/api/list")
    public List<Board> list() {
        return repo.findAll();   // => JSON 배열
    }
}
