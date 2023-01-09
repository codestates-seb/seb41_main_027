package main027.server.domain.member.mapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T22:29:08+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( requestBody.getId() );
        member.setNickName( requestBody.getNickName() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String nickName = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        id = member.getId();
        email = member.getEmail();
        nickName = member.getNickName();
        createdAt = member.getCreatedAt();
        modifiedAt = member.getModifiedAt();

        MemberDto.Response response = new MemberDto.Response( id, email, nickName, createdAt, modifiedAt );

        return response;
    }
}
