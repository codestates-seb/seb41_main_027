package main027.server.domain.place.repository;

import main027.server.domain.place.entity.Place;
import main027.server.domain.place.entity.PlaceLikeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceLikeUserRepository extends JpaRepository<PlaceLikeUser, Long> {
    @Query("select b from PlaceLikeUser b where b.member.memberId = :memberId and b.place.placeId = :placeId")
    Optional<PlaceLikeUser> checkLiked(@Param("memberId") Long memberId, @Param("placeId") Long placeId);

    @Query("select b.place from PlaceLikeUser b where b.member.memberId = :memberId")
    Page<Place> findLikes(@Param("memberId") Long memberId, Pageable pageable);
}
