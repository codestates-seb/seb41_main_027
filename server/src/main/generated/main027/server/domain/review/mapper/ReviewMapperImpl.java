package main027.server.domain.review.mapper;

import javax.annotation.processing.Generated;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.entity.Review;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T20:59:55+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review PostToEntity(ReviewDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setEmoji( postToEmoji( postDto ) );
        review.setContent( postDto.getContent() );

        return review;
    }

    @Override
    public ReviewDto.Response entityToResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto.Response response = new ReviewDto.Response();

        response.setEmojiId( reviewEmojiEmojiId( review ) );
        response.setReviewId( review.getReviewId() );
        response.setContent( review.getContent() );
        response.setCreatedAt( review.getCreatedAt() );

        return response;
    }

    protected Emoji postToEmoji(ReviewDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Emoji emoji = new Emoji();

        emoji.setEmojiId( post.getEmojiId() );

        return emoji;
    }

    private Long reviewEmojiEmojiId(Review review) {
        if ( review == null ) {
            return null;
        }
        Emoji emoji = review.getEmoji();
        if ( emoji == null ) {
            return null;
        }
        Long emojiId = emoji.getEmojiId();
        if ( emojiId == null ) {
            return null;
        }
        return emojiId;
    }
}
