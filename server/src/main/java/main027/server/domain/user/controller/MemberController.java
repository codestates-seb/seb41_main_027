package main027.server.domain.user.controller;

import main027.server.domain.user.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember() {
        return ResponseEntity.created(URI.create("/members/1")).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember() {
        MemberDto.Response response = new MemberDto.Response(1L,
                                                             "hgd@gmail.com",
                                                             "홍길동",
                                                             LocalDateTime.now(),
                                                             LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember() {
        return ResponseEntity.noContent().build();
    }
}
