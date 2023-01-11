package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.place.service.PlaceServiceImpl;
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
    private final PlaceServiceImpl placeService;
    private final MemberService memberService;

    public Review save(Review review) {
        // emoji, member, place가 존재하는지 검증
        emojiService.findVerifiedEmoji(review.getEmoji().getEmojiId());
        memberService.findVerifiedMember(review.getMember().getMemberId());
        placeService.findVerifiedPlace(review.getPlace().getPlaceId());

        return reviewRepository.save(review);
    }

    public Review findVerifiedReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }
}
