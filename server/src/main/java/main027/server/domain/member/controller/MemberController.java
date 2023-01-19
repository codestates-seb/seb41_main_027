package main027.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.mapper.MemberMapper;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.member.service.MemberUpdateService;
import main027.server.global.aop.logging.MemberHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@Validated
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberUpdateService memberUpdateService;
    private final MemberMapper mapper;
    private final MemberHolder memberHolder;

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member createdMember = memberService.createMember(mapper.memberPostDtoToMember(requestBody));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(createdMember), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody) {
        Member updatedMember = memberUpdateService.updateMember(mapper.memberPatchDtoToMember(requestBody,
                                                                                              memberHolder.getMemberId()));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(updatedMember), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteMember() {
        memberService.deleteMember(memberHolder.getMemberId());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
