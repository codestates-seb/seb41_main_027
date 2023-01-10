package main027.server.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    /**
     * @return 북마킹이 되어 있지 않았고 해당 로직을 통해 북마킹이 되었다면 true 리턴 <br>
     *         북마킹이 되어 있었고 해당 로직을 통해 북마킹이 해제되었다면 false 리턴
     */
    public Boolean changeBookmarkStatus(Bookmark bookmark) {
        // 해당 memberId, placeId로 등록된 북마킹이 있는지를 조회
        Optional<Bookmark> findBookmark = bookmarkRepository
                .checkBookmarked(bookmark.getMember().getMemberId(),
                                 bookmark.getPlace().getPlaceId());

        // 북마킹이 되어 있는지 여부 확인
        boolean isBookmarked = findBookmark.isPresent();

        // 북마킹이 되어 있었다면 해당 북마크를 삭제 하고 false를 반환
        if (isBookmarked) {
            bookmarkRepository.delete(findBookmark.get());
            return false;
        } else {
            bookmarkRepository.save(bookmark);
            return true;
        }

    }

}
