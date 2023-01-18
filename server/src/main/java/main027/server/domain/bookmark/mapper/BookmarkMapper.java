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
    Bookmark PostToEntity(BookmarkDto.Post postDto);

    /**
     * 주석 처리 된 메서드처럼 수동으로 작성해줘도 되고,
     * 자동 생성 메서드를 이용해 다음과 같이 위의 주석처리된 메서드와 작성해줘도 똑같이 동작한다.
     */
    default BookmarkDto.Response pageToList(Page<Place> pages) {
        BookmarkDto.Response response = pageToListChild(pages);
        response.setPresentPage(Long.valueOf(pages.getPageable().getPageNumber() + 1));

        return response;
    }

    @Mapping(source = "content", target = "placeList")
    BookmarkDto.Response pageToListChild(Page<Place> places);
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "category.name", target = "category")
        // TODO: categoryId로 변경 해야 함
    PlaceDto.PlaceResponseDto placeToResponse(Place place);

    /**
     * Page<Place>를 받아서 Response로 바꿔주는 customMapper 메서드 <br>
     */
/*
    default BookmarkDto.Response pageToList(Page<Place> pages) {
        BookmarkDto.Response response = new BookmarkDto.Response();

        List<PlaceDto.PlaceResponseDto> placeList = pages.getContent().stream()
                                                         .map(place -> placeToResponse(place))
                                                         .collect(Collectors.toList());

        response.setPlaceList(placeList);
        response.setTotalPage(Long.valueOf(pages.getTotalPages()));
        response.setTotalElements(pages.getTotalElements());
        response.setPresentPage(Long.valueOf(pages.getPageable().getPageNumber()) + 1);

        return response;
    }
*/


    /**
     * 위 처럼 수동으로 작성해줘도 되고, 자동 생성 메서드를 이용해 다음과 같이 위의 주석처리된 메서드와 작성해줘도 똑같이 동작한다.
     */
    default BookmarkDto.Response pageToList(Page<Place> pages) {
        BookmarkDto.Response response = pageToListChild(pages);
        response.setPresentPage(Long.valueOf(pages.getPageable().getPageNumber() + 1));

        return response;
    }

    @Mapping(source = "content", target = "placeList")
    BookmarkDto.Response pageToListChild(Page<Place> places);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "likeCount", expression = "java(place.getPlaceLikeUserList().size())")
        // TODO: categoryId로 변경 해야 함
    PlaceDto.PlaceResponseDto placeToResponse(Place place);

}
