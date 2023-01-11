package main027.server.domain.member.controller;

import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.mapper.MemberMapper;
import main027.server.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member createdMember = memberService.createMember(mapper.memberPostDtoToMember(requestBody));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(createdMember), HttpStatus.CREATED);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody,
                                      @Positive @PathVariable("memberId") Long memberId) {
        requestBody.setMemberId(memberId);
        Member updatedMember = memberService.updateMember(mapper.memberPatchDtoToMember(requestBody));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(updatedMember), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@Positive @PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
