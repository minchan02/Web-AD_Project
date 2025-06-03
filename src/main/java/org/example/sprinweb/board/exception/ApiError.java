package org.example.sprinweb.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;   // "Not Found"
    private final String message; // 개발자가 넘긴 설명
    private final String path;    // 요청 URI
}

