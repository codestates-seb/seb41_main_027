package main027.server.domain.review.mapper;

import javax.annotation.processing.Generated;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-06T00:06:12+0900",
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

        review.setContent( postDto.getContent() );
        review.setEmoji( postDto.getEmoji() );

        return review;
    }

    @Override
    public ReviewDto.Response entityToResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto.Response response = new ReviewDto.Response();

        response.setId( review.getId() );
        response.setContent( review.getContent() );
        response.setEmoji( review.getEmoji() );
        response.setCreatedAt( review.getCreatedAt() );

        return response;
    }
}
