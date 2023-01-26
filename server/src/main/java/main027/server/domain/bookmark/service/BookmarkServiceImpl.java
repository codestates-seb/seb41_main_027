package main027.server.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.repository.BookmarkRepository;
import main027.server.domain.member.verifier.MemberVerifier;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.verifier.PlaceVerifier;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberVerifier memberVerifier;
    private final PlaceVerifier placeVerifier;

    /**
     * 해당 member와 place가 있는지 조회 -> 없으면 해당 메서드의 Exception 발생. <br>
     * <p>
     * 해당 memberId, placeId로 등록된 북마킹이 있는지를 조회 ->
     * 되어 있었다면 북마크 삭제후 false 반환, 그렇지 않다면 북마크를 추가하고 true를 반환
     *
     * @return 북마킹이 되어 있지 않았고 해당 로직을 통해 북마킹이 되었다면 true 리턴 <br>
     * 북마킹이 되어 있었고 해당 로직을 통해 북마킹이 해제되었다면 false 리턴
     * @see MemberVerifier#findVerifiedMember
     * @see PlaceVerifier#findVerifiedPlace
     */
    @TimeTrace
    public Boolean changeBookmarkStatus(Bookmark bookmark) {
        memberVerifier.findVerifiedMember(bookmark.getMember().getMemberId());
        placeVerifier.findVerifiedPlace(bookmark.getPlace().getPlaceId());

        Optional<Bookmark> findBookmark = bookmarkRepository
                .checkBookmarked(bookmark.getMember().getMemberId(),
                                 bookmark.getPlace().getPlaceId());

        boolean isBookmarked = findBookmark.isPresent();

        if (isBookmarked) {
            bookmarkRepository.delete(findBookmark.get());
            return false;
        } else {
            bookmarkRepository.save(bookmark);
            return true;
        }

    }

    /**
     * @return Member가 북마킹한 장소 Page<Place>
     */
    @TimeTrace
    public Page<Place> findPlaceMemberBookmarked(Long memberId, Pageable pageable) {
        // 존재하는 memberId인지 검증
        memberVerifier.findVerifiedMember(memberId);

        return bookmarkRepository.findBookmark(memberId, pageable);
    }

}
