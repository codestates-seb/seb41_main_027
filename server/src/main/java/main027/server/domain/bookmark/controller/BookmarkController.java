package main027.server.domain.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.mapper.BookmarkMapper;
import main027.server.domain.bookmark.service.BookmarkService;
import main027.server.domain.place.entity.Place;
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

    @TimeTrace
    @PostMapping
    public ResponseEntity post(@Validated @RequestBody BookmarkDto.Post postDto) {
        Boolean finalBookmarkStatus = bookmarkService.changeBookmarkStatus(mapper.PostToEntity(postDto));
        return new ResponseEntity(finalBookmarkStatus, HttpStatus.OK);
    }

    /**
     * @param memberId 의 북마크 리스트를 요청
     * @param page     클라이언트가 요청할 페이지 수
     * @return {@link BookmarkDto.Response} 페이징 처리 된 북마크리스 트 리턴 Book
     */
    @TimeTrace
    @GetMapping("/{memberId}")
    public ResponseEntity getList(@PathVariable Long memberId,
                                  @RequestParam(defaultValue = "1") int page) {

        BookmarkDto.Response response = mapper.pageToList(
                bookmarkService.findPlaceMemberBookmarked(memberId, PageRequest.of(page - 1, 10)));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
