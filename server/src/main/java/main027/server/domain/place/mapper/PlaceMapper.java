package main027.server.domain.place.mapper;

import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PlaceMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto);

    Place placePatchDtoToPlace(PlaceDto.PlacePatchDto placePatchDto);

    @Mapping(source = "content", target = "placeList")
    @Mapping(target = "presentPage", expression = "java(Long.valueOf(pages.getPageable().getPageNumber() + 1))")
    PlaceDto.PageResponseDto pageToList(Page<Place> pages);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "likeCount", expression = "java(place.getPlaceLikeUserList().size())")
    @Mapping(target = "reviewCount", expression = "java(place.getReviewList().size())")
    PlaceDto.PlaceResponseDto placeToPlaceResponseDto(Place place);

}
