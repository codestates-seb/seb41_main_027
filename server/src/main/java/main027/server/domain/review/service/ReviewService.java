package main027.server.domain.review.service;

import main027.server.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Review save(Review review);

    Page<Review> findReviews(Long placeId, Pageable pageable);
}
