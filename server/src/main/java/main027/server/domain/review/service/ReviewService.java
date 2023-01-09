package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EmojiService emojiService;

    public Review save(Review review) {
        emojiService.verifyExistEmoji(review.getEmoji().getEmojiId());
        return reviewRepository.save(review);
    }

    public Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new RuntimeException("Review Not Found")); // 예외 리팩토링 필요
    }

}
