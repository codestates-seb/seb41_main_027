package main027.server.domain.bookmark.service;

import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkService {

    Boolean changeBookmarkStatus(Bookmark bookmark);

    Page<Place> findPlaceMemberBookmarked(Long memberId, Pageable pageable);
}
