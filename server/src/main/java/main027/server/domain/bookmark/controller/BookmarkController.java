package main027.server.domain.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.mapper.BookmarkMapper;
import main027.server.domain.bookmark.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper mapper;

    @PostMapping
    public ResponseEntity post(@Validated @RequestBody BookmarkDto.Post postDto) {
        Bookmark bookmark = mapper.PostToEntity(postDto);
        Boolean finalBookmarkStatus = bookmarkService.changeBookmarkStatus(bookmark);
        return new ResponseEntity(finalBookmarkStatus, HttpStatus.OK);
    }
}
