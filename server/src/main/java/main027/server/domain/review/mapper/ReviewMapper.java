package main027.server.domain.review.mapper;

import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review PostToEntity(ReviewDto.Post postDto);

    ReviewDto.Response entityToResponse(Review review);
}
