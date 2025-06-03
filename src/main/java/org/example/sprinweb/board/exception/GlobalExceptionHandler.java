package org.example.sprinweb.board.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ApiError> notFound(BoardNotFoundException ex,
                                             HttpServletRequest req) {
        ApiError body = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // 그 밖의 모든 예외 → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> internal(Exception ex,
                                             HttpServletRequest req) {
        ApiError body = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "예상치 못한 오류가 발생했습니다.",
                req.getRequestURI());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
