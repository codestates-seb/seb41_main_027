package main027.server.domain.member.mapper;

import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);

    Member memberPatchDtoToMember(MemberDto.Patch requestBody, Long memberId);

    MemberDto.Response memberToMemberResponseDto(Member member);
}
