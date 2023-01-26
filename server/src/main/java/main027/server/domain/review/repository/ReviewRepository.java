package main027.server.domain.review.repository;

import main027.server.domain.review.entity.Review;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * @param placeId 리뷰 리스트를 가져올 Place의 Id
     * @return Place에 속해 있는 Paging 처리된 Review 리스트
     */
    @TimeTrace
    @Query("select r from review r where r.place.placeId = :placeId order by r.createdAt desc")
    Page<Review> findReviews(@Param("placeId") Long placeId, Pageable pageable);
}
