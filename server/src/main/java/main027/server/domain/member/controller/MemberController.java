package main027.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.mapper.MemberMapper;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.member.service.MemberUpdateService;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@Validated
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberUpdateService memberUpdateService;
    private final MemberMapper mapper;
    private final DataHolder dataHolder;

    @TimeTrace
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member createdMember = memberService.createMember(mapper.memberPostDtoToMember(requestBody));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(createdMember), HttpStatus.CREATED);
    }

    @TimeTrace
    @PatchMapping
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody) {
        Member updatedMember = memberUpdateService.updateMember(mapper.memberPatchDtoToMember(requestBody,
                                                                                              dataHolder.getMemberId()));
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(updatedMember), HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping
    public ResponseEntity getMyInfo(){
        Member getMember = memberService.findMember(dataHolder.getMemberId());
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(getMember),HttpStatus.OK);

    }



    @TimeTrace
    @DeleteMapping
    public ResponseEntity deleteMember() {
        memberService.deleteMember(dataHolder.getMemberId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}