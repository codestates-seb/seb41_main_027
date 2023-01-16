package main027.server.domain.bookmark.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Category;
import main027.server.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T10:44:05+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Azul Systems, Inc.)"
)
@Component
public class BookmarkMapperImpl implements BookmarkMapper {

    @Override
    public Bookmark PostToEntity(BookmarkDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        Bookmark bookmark = new Bookmark();

        bookmark.setMember( postToMember( postDto ) );
        bookmark.setPlace( postToPlace( postDto ) );

        return bookmark;
    }

    @Override
    public BookmarkDto.Response pageToListChild(Page<Place> places) {
        if ( places == null ) {
            return null;
        }

        BookmarkDto.Response response = new BookmarkDto.Response();

        if ( places.hasContent() ) {
            response.setPlaceList( placeListToPlaceResponseDtoList( places.getContent() ) );
        }
        response.setTotalPages( (long) places.getTotalPages() );
        response.setTotalElements( places.getTotalElements() );

        return response;
    }

    @Override
    public PlaceDto.PlaceResponseDto placeToResponse(Place place) {
        if ( place == null ) {
            return null;
        }

        Long memberId = null;
        String category = null;
        Long placeId = null;
        String name = null;
        String address = null;
        String description = null;
        Long kakaoId = null;
        String latitude = null;
        String longitude = null;

        memberId = placeMemberMemberId( place );
        category = placeCategoryName( place );
        placeId = place.getPlaceId();
        name = place.getName();
        address = place.getAddress();
        description = place.getDescription();
        kakaoId = place.getKakaoId();
        latitude = place.getLatitude();
        longitude = place.getLongitude();

        PlaceDto.PlaceResponseDto placeResponseDto = new PlaceDto.PlaceResponseDto( placeId, memberId, name, address, description, kakaoId, category, latitude, longitude );

        return placeResponseDto;
    }

    protected Member postToMember(BookmarkDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( post.getMemberId() );

        return member;
    }

    protected Place postToPlace(BookmarkDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Place place = new Place();

        place.setPlaceId( post.getPlaceId() );

        return place;
    }

    protected List<PlaceDto.PlaceResponseDto> placeListToPlaceResponseDtoList(List<Place> list) {
        if ( list == null ) {
            return null;
        }

        List<PlaceDto.PlaceResponseDto> list1 = new ArrayList<PlaceDto.PlaceResponseDto>( list.size() );
        for ( Place place : list ) {
            list1.add( placeToResponse( place ) );
        }

        return list1;
    }

    private Long placeMemberMemberId(Place place) {
        if ( place == null ) {
            return null;
        }
        Member member = place.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }

    private String placeCategoryName(Place place) {
        if ( place == null ) {
            return null;
        }
        Category category = place.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
