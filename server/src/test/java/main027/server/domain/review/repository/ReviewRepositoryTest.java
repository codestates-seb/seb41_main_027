//package main027.server.domain.review.repository;
//
//import lombok.extern.slf4j.Slf4j;
//import main027.server.domain.review.entity.Review;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@DataJpaTest
//class ReviewRepositoryTest {
//
//    @Autowired
//    ReviewRepository reviewRepository;
//
//    @Test
//    @DisplayName("리뷰 저장")
//    void saveReview() {
//        //given
//        Review review = new Review();
//        review.setContent("추천합니다");
//
//        //when
//        Review savedReview = reviewRepository.save(review);
//
//        //then
//        assertThat(savedReview.getContent()).isEqualTo(review.getContent());
//        assertThat(savedReview.getEmoji()).isEqualTo(review.getEmoji());
//        assertThat(savedReview.getReviewId()).isEqualTo(1L);
//    }
//
//}