package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.security.UserDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

  @GetMapping("/auth")
  public ResponseEntity<HttpStatus> auth() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetail user = (UserDetail) authentication.getPrincipal();
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }
}
