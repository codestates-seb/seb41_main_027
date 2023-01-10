package main027.server.domain.bookmark.repository;

import main027.server.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select b from Bookmark b where b.member.memberId = :memberId and b.place.placeId = :placeId")
    Optional<Bookmark> checkBookmarked(@Param("memberId") Long memberId, @Param("placeId") Long placeId);

    // @Modifying
    // @Query("delete b from Bookmark b where b.member.memberId = :memberId and b.place.placeId = :palceId")
    // int removeBookmark(@Param("memberId") Long memberId, @Param("placeId") Long placeId);
}
