package main027.server.domain.bookmark.repository;

import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookmarkRepositoryTest {

    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PlaceRepository placeRepository;

    @BeforeAll
    void beforeAll() {
        addMember("hgd@gmail.com", "홍길동");
        addPlace("장소이름", 1L, 2L);
    }

//    @Test
//    void findBookmarks() {
//        Member member = new Member();
//        member.setMemberId(1L);
//        Place place = new Place();
//        place.setPlaceId(1L);
//        Bookmark bookmark = new Bookmark();
//        bookmark.setMember(member);
//        bookmark.setPlace(place);
//
//        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
//
//        assertThat(savedBookmark.getMember().getMemberId()).isEqualTo(member.getMemberId());
//        assertThat(savedBookmark.getPlace().getPlaceId()).isEqualTo(place.getPlaceId());
//    }


    private void addMember(String email, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setNickName(name);
        member.setPassword("1111");
        memberRepository.save(member);
    }

    private void addPlace(String name, Long memberId, Long kakaoId) {
        Place place = new Place();
        place.setName(name);
        place.setMember(new Member());
        place.getMember().setMemberId(memberId);
        place.setKakaoId(kakaoId);
        place.setAddress("주소입니다.");
        place.setDescription("설명입니다.");
        place.setLatitude("1111");
        place.setLongitude("2222");
        placeRepository.save(place);
    }
}