package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.repository.ReviewRepository;
import main027.server.global.exception.BusinessLogicException;
import main027.server.global.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EmojiService emojiService;

    public Review save(Review review) {
        emojiService.findVerifiedEmoji(review.getEmoji().getEmojiId());
        return reviewRepository.save(review);
    }

    public Review findVerifiedReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }
}
