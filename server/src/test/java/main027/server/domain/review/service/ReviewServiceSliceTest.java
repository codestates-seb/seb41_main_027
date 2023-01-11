package main027.server.domain.review.service;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.service.PlaceServiceImpl;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceSliceTest {

    @Mock
    ReviewRepository reviewRepository;
    @Mock
    EmojiService emojiService;
    @Mock
    MemberService memberService;
    @Mock
    PlaceServiceImpl placeService;

    @InjectMocks
    ReviewService reviewService;

    @Test
    @DisplayName("리뷰 저장")
    void saveReview() {

        // given
        Review review = createReview();

        // mocking
        when(reviewRepository.save(any())).thenReturn(review);

        // when
        Review savedReview = reviewService.save(review);

        // then
        assertThat(savedReview.getContent()).isEqualTo(review.getContent());
        assertThat(savedReview.getEmoji()).isEqualTo(review.getEmoji());
    }





    private Emoji createEmoji() {
        Emoji emoji = new Emoji();
        emoji.setEmojiId(1L);
        emoji.setName("웃음");
        return emoji;
    }

    private Member createMember() {
        Member member = new Member();
        member.setMemberId(1L);
        return member;
    }

    private Place createPlace() {
        Place place = new Place();
        place.setPlaceId(1L);
        return place;
    }

    private Review createReview() {
        Review review = new Review();
        review.setReviewId(1L);
        review.setContent("리뷰 내용입니다.");
        review.setEmoji(createEmoji());
        review.setMember(createMember());
        review.setPlace(createPlace());

        return review;
    }
}
