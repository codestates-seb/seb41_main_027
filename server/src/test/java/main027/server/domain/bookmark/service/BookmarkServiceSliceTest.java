package main027.server.domain.bookmark.service;

import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.repository.BookmarkRepository;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.service.PlaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceSliceTest {

    @Mock
    BookmarkRepository bookmarkRepository;
    @Mock
    MemberService memberService;
    @Mock
    PlaceService placeService;

    @InjectMocks
    BookmarkService bookmarkService;

    @Test
    @DisplayName("북마크 추가 기능")
    void addBookmark() {

        // given
        Member member = new Member();
        member.setMemberId(1L);
        Place place = new Place();
        place.setPlaceId(2L);
        Bookmark bookmark = new Bookmark(member, place);
        Optional<Bookmark> optBookmark = Optional.ofNullable(new Bookmark());

        when(memberService.findVerifiedMember(anyLong())).thenReturn(null);
        when(placeService.findVerifiedPlace(anyLong())).thenReturn(null);
        when(bookmarkRepository.checkBookmarked(anyLong(), anyLong())).thenReturn(optBookmark);

        // when
        Boolean isBookmarked = bookmarkService.changeBookmarkStatus(bookmark);

        // then
        assertThat(isBookmarked).isFalse();
    }

    @Test
    @DisplayName("북마크 제거 기능")
    void removeBookmark() {

        // given
        Member member = new Member();
        member.setMemberId(1L);
        Place place = new Place();
        place.setPlaceId(2L);
        Bookmark bookmark = new Bookmark(member, place);
        Optional<Bookmark> nullBookmark = Optional.ofNullable(null);

        when(memberService.findVerifiedMember(anyLong())).thenReturn(null);
        when(placeService.findVerifiedPlace(anyLong())).thenReturn(null);
        when(bookmarkRepository.checkBookmarked(anyLong(), anyLong())).thenReturn(nullBookmark);

        // when
        Boolean isBookmarked = bookmarkService.changeBookmarkStatus(bookmark);

        // then
        assertThat(isBookmarked).isTrue();
    }

}
