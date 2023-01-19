package main027.server.domain.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.mapper.BookmarkMapper;
import main027.server.domain.bookmark.service.BookmarkService;
import main027.server.domain.place.entity.Place;
import main027.server.global.aop.logging.MemberHolder;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper mapper;
    private final MemberHolder memberHolder;

    @TimeTrace
    @PostMapping("/{placeId}")
    public ResponseEntity post(@PathVariable Long placeId) {
        Bookmark bookmark = mapper.PostToEntity(placeId, memberHolder.getMemberId());
        Boolean finalBookmarkStatus = bookmarkService.changeBookmarkStatus(bookmark);
        return new ResponseEntity(finalBookmarkStatus, HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping
    public ResponseEntity getList(@RequestParam(defaultValue = "1") int page) {
        Long memberId = memberHolder.getMemberId();
        Page<Place> pagingList = bookmarkService.findPlaceMemberBookmarked(memberId, PageRequest.of(page-1, 10));

        BookmarkDto.Response response = mapper.pageToList(pagingList);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
