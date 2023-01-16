package main027.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import main027.server.domain.member.verifier.MemberVerifier;
import main027.server.domain.place.verifier.PlaceVerifier;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.repository.ReviewRepository;
import main027.server.domain.review.verifier.EmojiVerifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final EmojiVerifier emojiVerifier;
    private final PlaceVerifier placeVerifier;
    private final MemberVerifier memberVerifier;

    public Review save(Review review) {
        // emoji, member, place가 존재하는지 검증
        emojiVerifier.findVerifiedEmoji(review.getEmoji().getEmojiId());
        memberVerifier.findVerifiedMember(review.getMember().getMemberId());
        placeVerifier.findVerifiedPlace(review.getPlace().getPlaceId());

        return reviewRepository.save(review);
    }

    /**
     * 페이징 처리 된 Review를 리턴하는 서비스 메서드
     * @return 페이징 처리 된 Review
     */
    @Override
    public Page<Review> findReviews(Long placeId, Pageable pageable) {
        placeVerifier.findVerifiedPlace(placeId);

        return reviewRepository.findReviews(placeId, pageable);
    }
}
