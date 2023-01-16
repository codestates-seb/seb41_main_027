package main027.server.domain.member.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T10:44:05+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( requestBody.getEmail() );
        member.setPassword( requestBody.getPassword() );
        member.setNickName( requestBody.getNickName() );

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( requestBody.getMemberId() );
        member.setPassword( requestBody.getPassword() );
        member.setNickName( requestBody.getNickName() );
        member.setMemberStatus( requestBody.getMemberStatus() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        List<String> roles = null;
        Long memberId = null;
        String email = null;
        String nickName = null;
        Member.MemberStatus memberStatus = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        List<String> list = member.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }
        memberId = member.getMemberId();
        email = member.getEmail();
        nickName = member.getNickName();
        memberStatus = member.getMemberStatus();
        createdAt = member.getCreatedAt();
        modifiedAt = member.getModifiedAt();

        MemberDto.Response response = new MemberDto.Response( memberId, email, nickName, memberStatus, roles, createdAt, modifiedAt );

        return response;
    }
}
