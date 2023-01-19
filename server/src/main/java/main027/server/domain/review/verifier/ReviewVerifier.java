package main027.server.domain.review.verifier;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.repository.ReviewRepository;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewVerifier {

    private final ReviewRepository reviewRepository;

    public Review findVerifiedReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }
}
