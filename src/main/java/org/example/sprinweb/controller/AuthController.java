package org.example.sprinweb.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.sprinweb.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping(value = "/auth/register", consumes = "application/x-www-form-urlencoded")
    public String registerForm(@RequestParam String username,
                               @RequestParam String password) {
        userService.register(username, password);
        return "redirect:/login?registered";
    }

    @PostMapping(value = "/auth/register", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> registerJson(@RequestBody Map<String, String> dto) {
        userService.register(dto.get("username"), dto.get("password"));
        return ResponseEntity.ok("registered");
    }

    @PostMapping(value = "/auth/login", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Map<String, String> dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.get("username"), dto.get("password"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok("login ok");
    }
}
