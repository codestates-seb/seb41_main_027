package main027.server.domain.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.mapper.BookmarkMapper;
import main027.server.domain.bookmark.service.BookmarkService;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;
    private final PlaceMapper placeMapper;
    private final DataHolder dataHolder;

    @TimeTrace
    @PostMapping("/{placeId}")
    public ResponseEntity post(@PathVariable Long placeId) {
        Boolean finalBookmarkStatus = bookmarkService.changeBookmarkStatus(
                bookmarkMapper.PostToEntity(dataHolder.getMemberId(), placeId));

        return new ResponseEntity(finalBookmarkStatus, HttpStatus.OK);
    }

    /**
     * @param page 클라이언트가 요청할 페이지 수
     * @return {@link BookmarkDto.Response} 페이징 처리 된 북마크리스트 리턴 Book
     */
    @TimeTrace
    @GetMapping
    public ResponseEntity getList(@RequestParam(defaultValue = "1") int page) {

        Long memberId = dataHolder.getMemberId();
        Page<Place> pagingList = bookmarkService.findPlaceMemberBookmarked(memberId, PageRequest.of(page-1, 10));

        PlaceDto.PageResponseDto response = placeMapper.pageToList(pagingList, memberId);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
