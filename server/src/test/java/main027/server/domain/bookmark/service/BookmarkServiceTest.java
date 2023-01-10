package main027.server.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.repository.BookmarkRepository;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.repository.PlaceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
@TestPropertySource(locations = "/application-jpaLog.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookmarkServiceTest {

    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    BookmarkService bookmarkService;

    @BeforeAll
    void beforeAll() {
        addMember("hgd@gmail.com", "홍길동");
        addMember("kkd@gmail.com", "김코딩");
        addPlace("길이식당", 1L, 1L);
        addPlace("미분당 건대점", 1L, 2L);
        addBookmark(1L, 1L);
        addBookmark(1L, 2L);
    }

    @Transactional
    @Test
    void isSaved() {
        Bookmark bookmark = bookmarkRepository.findById(2L).get();
        log.info("bookmark.memberId={}, placeId={}", bookmark.getMember().getNickName(),
                 bookmark.getPlace().getName());
    }

    @Transactional
    @Test
    void bookmark() {
        Member member = new Member();
        member.setMemberId(1L);
        Place place = new Place();
        place.setPlaceId(1L);
        Bookmark bookmark = new Bookmark(member, place);
        Boolean isBookmarked = bookmarkService.changeBookmarkStatus(bookmark);

        assertThat(isBookmarked).isFalse();

        Boolean isBookmarked2 = bookmarkService.changeBookmarkStatus(bookmark);

        assertThat(isBookmarked2).isTrue();
    }

    private void addMember(String email, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setNickName(name);
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
        place.setLatitude(1111L);
        place.setLongitude(2222L);
        placeRepository.save(place);
    }

    private void addBookmark(Long memberId, Long placeId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setMember(new Member());
        bookmark.setPlace(new Place());
        bookmark.getMember().setMemberId(memberId);
        bookmark.getPlace().setPlaceId(placeId);
        bookmarkRepository.save(bookmark);
    }

}