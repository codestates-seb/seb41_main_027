package main027.server.domain.place.mapper;

import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface PlaceMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "placePostDto.categoryId", target = "category.categoryId")
    Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto, Long memberId);

    Place placePatchDtoToPlace(PlaceDto.PlacePatchDto placePatchDto);

    default PlaceDto.PageResponseDto pageToList(Page<Place> pages, Long memberId) {
        PlaceDto.PageResponseDto result = new PlaceDto.PageResponseDto();

        List<PlaceDto.PlaceResponseDto> list = pages.getContent().stream()
                                                       .map(content -> placeToPlaceResponseDto(content, memberId))
                                                       .collect(Collectors.toList());

        result.setPlaceList(list);
        result.setTotalPages(Long.valueOf(pages.getTotalPages()));
        result.setPresentPage(Long.valueOf(pages.getPageable().getPageNumber() + 1));
        result.setTotalElements(pages.getTotalElements());

        return result;
    }

    default PlaceDto.PlaceResponseDto placeToPlaceResponseDto(Place place, Long memberId) {
        PlaceDto.PlaceResponseDto result = new PlaceDto.PlaceResponseDto();

        result.setPlaceId(place.getPlaceId());
        result.setMemberId(place.getMember().getMemberId());
        result.setName(place.getName());
        result.setAddress(place.getAddress());
        result.setDescription(place.getDescription());
        result.setLikeCount(place.getPlaceLikeUserList().size());
        result.setKakaoId(place.getKakaoId());
        result.setCategory(place.getCategory().getName());
        result.setLatitude(place.getLatitude());
        result.setLongitude(place.getLongitude());
        result.setCreatedAt(place.getCreatedAt());

        boolean isBookmarked = place.getBookmarkList().stream()
                                    .anyMatch(bookmark -> bookmark.getMember().getMemberId() == memberId);

        boolean isLiked = place.getPlaceLikeUserList().stream()
                               .anyMatch(placeLikeUser -> placeLikeUser.getMember().getMemberId() == memberId);

        result.setIsBookMarked(isBookmarked);
        result.setIsLiked(isLiked);

        return result;
    }

}
