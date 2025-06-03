package org.example.sprinweb.board.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String msg) { super(msg); }
}