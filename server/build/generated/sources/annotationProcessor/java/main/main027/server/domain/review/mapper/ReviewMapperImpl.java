package main027.server.domain.review.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.entity.Place;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Emoji;
import main027.server.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T10:44:05+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Azul Systems, Inc.)"
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
        review.setMember( postToMember( postDto ) );
        review.setPlace( postToPlace( postDto ) );
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
        response.setMemberId( reviewMemberMemberId( review ) );
        response.setPlaceId( reviewPlacePlaceId( review ) );
        response.setReviewId( review.getReviewId() );
        response.setContent( review.getContent() );
        response.setCreatedAt( review.getCreatedAt() );

        return response;
    }

    @Override
    public ReviewDto.ListResponse pageToResponseListChild(Page<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        ReviewDto.ListResponse listResponse = new ReviewDto.ListResponse();

        if ( reviews.hasContent() ) {
            listResponse.setReviewList( reviewListToResponseList( reviews.getContent() ) );
        }
        listResponse.setTotalPages( (long) reviews.getTotalPages() );
        listResponse.setTotalElements( reviews.getTotalElements() );

        return listResponse;
    }

    protected Emoji postToEmoji(ReviewDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Emoji emoji = new Emoji();

        emoji.setEmojiId( post.getEmojiId() );

        return emoji;
    }

    protected Member postToMember(ReviewDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( post.getMemberId() );

        return member;
    }

    protected Place postToPlace(ReviewDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Place place = new Place();

        place.setPlaceId( post.getPlaceId() );

        return place;
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

    private Long reviewMemberMemberId(Review review) {
        if ( review == null ) {
            return null;
        }
        Member member = review.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }

    private Long reviewPlacePlaceId(Review review) {
        if ( review == null ) {
            return null;
        }
        Place place = review.getPlace();
        if ( place == null ) {
            return null;
        }
        Long placeId = place.getPlaceId();
        if ( placeId == null ) {
            return null;
        }
        return placeId;
    }

    protected List<ReviewDto.Response> reviewListToResponseList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewDto.Response> list1 = new ArrayList<ReviewDto.Response>( list.size() );
        for ( Review review : list ) {
            list1.add( entityToResponse( review ) );
        }

        return list1;
    }
}
