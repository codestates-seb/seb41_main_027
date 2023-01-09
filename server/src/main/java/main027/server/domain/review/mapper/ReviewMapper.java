package main027.server.domain.review.mapper;

import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "emojiId", target = "emoji.emojiId")
    Review PostToEntity(ReviewDto.Post postDto);

    @Mapping(source = "emoji.emojiId", target = "emojiId")
    ReviewDto.Response entityToResponse(Review review);
    
}
