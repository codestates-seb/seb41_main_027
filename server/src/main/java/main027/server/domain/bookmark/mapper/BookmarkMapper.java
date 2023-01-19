package main027.server.domain.bookmark.mapper;

import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "placeId", target = "place.placeId")
    Bookmark PostToEntity(Long placeId, Long memberId);


    @Mapping(source = "content", target = "placeList")
    @Mapping(target = "presentPage", expression = "java(Long.valueOf(pages.getPageable().getPageNumber() + 1))")
    BookmarkDto.Response pageToList(Page<Place> pages);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "likeCount", expression = "java(place.getPlaceLikeUserList().size())")
    PlaceDto.PlaceResponseDto placeToResponse(Place place);
}
