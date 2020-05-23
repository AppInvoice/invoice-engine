package com.invoice.controller;

import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import com.invoice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/retrieve-balance")
    public ResponseEntity<FinalBalancePayload> retrieveUserBalance(@RequestParam final String email) {
        return ResponseEntity.ok(userService.retrieveFinalBalance(email));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserPayload>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
