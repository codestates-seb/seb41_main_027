package main027.server.domain.bookmark.repository;

import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.place.entity.Place;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @TimeTrace
    @Query("select b from Bookmark b where b.member.memberId = :memberId and b.place.placeId = :placeId")
    Optional<Bookmark> checkBookmarked(@Param("memberId") Long memberId, @Param("placeId") Long placeId);

    @TimeTrace
    @Query("select b.place from Bookmark b where b.member.memberId = :memberId")
    Page<Place> findBookmark(@Param("memberId") Long memberId, Pageable pageable);
    // @Modifying
    // @Query("delete b from Bookmark b where b.member.memberId = :memberId and b.place.placeId = :palceId")
    // int removeBookmark(@Param("memberId") Long memberId, @Param("placeId") Long placeId);
}
