package main027.server.domain.review.repository;

import main027.server.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from review r where r.place.placeId = :placeId")
    Page<Review> findReviews(@Param("placeId") Long placeId, Pageable pageable);
}
