//package main027.server.domain.bookmark.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import main027.server.domain.bookmark.entity.Bookmark;
//import main027.server.domain.member.entity.Member;
//import main027.server.domain.place.entity.Place;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@RequiredArgsConstructor
//@Slf4j
//// @TestPropertySource(locations = "/application-jpaLog.properties")
//@ActiveProfiles("stub")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class BookmarkServiceTest {
//
//    @Autowired
//    BookmarkService bookmarkService;
//
//
//    @Transactional
//    @Test
//    @DisplayName("북마크 상태 변경")
//    void bookmark() {
//        Member member = new Member();
//        member.setMemberId(1L);
//        Place place = new Place();
//        place.setPlaceId(1L);
//        Bookmark bookmark = new Bookmark(member, place);
//        Boolean isBookmarked = bookmarkService.changeBookmarkStatus(bookmark);
//
//        assertThat(isBookmarked).isTrue();
//
//        Boolean isBookmarked2 = bookmarkService.changeBookmarkStatus(bookmark);
//
//        assertThat(isBookmarked2).isFalse();
//    }
//
//}