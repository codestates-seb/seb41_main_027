package main027.server.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity postUser() {
        return ResponseEntity.created(null).build();
    }

    @PatchMapping
    public ResponseEntity patchUser() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity deleteUser() {
        return ResponseEntity.noContent().build();
    }



}
