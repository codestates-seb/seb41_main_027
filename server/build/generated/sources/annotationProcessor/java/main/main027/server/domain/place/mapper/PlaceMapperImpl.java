package main027.server.domain.place.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
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
public class PlaceMapperImpl implements PlaceMapper {

    @Override
    public Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto) {
        if ( placePostDto == null ) {
            return null;
        }

        Place place = new Place();

        place.setMember( placePostDtoToMember( placePostDto ) );
        place.setCategory( placePostDtoToCategory( placePostDto ) );
        place.setName( placePostDto.getName() );
        place.setAddress( placePostDto.getAddress() );
        place.setDescription( placePostDto.getDescription() );
        place.setKakaoId( placePostDto.getKakaoId() );
        place.setLatitude( placePostDto.getLatitude() );
        place.setLongitude( placePostDto.getLongitude() );

        return place;
    }

    @Override
    public Place placePatchDtoToPlace(PlaceDto.PlacePatchDto placePatchDto) {
        if ( placePatchDto == null ) {
            return null;
        }

        Place place = new Place();

        place.setPlaceId( placePatchDto.getPlaceId() );
        place.setDescription( placePatchDto.getDescription() );

        return place;
    }

    @Override
    public PlaceDto.PageResponseDto pageToListChild(Page<Place> places) {
        if ( places == null ) {
            return null;
        }

        PlaceDto.PageResponseDto pageResponseDto = new PlaceDto.PageResponseDto();

        if ( places.hasContent() ) {
            pageResponseDto.setPlaceList( placeListToResponseDto( places.getContent() ) );
        }
        pageResponseDto.setTotalPages( (long) places.getTotalPages() );
        pageResponseDto.setTotalElements( places.getTotalElements() );

        return pageResponseDto;
    }

    @Override
    public List<PlaceDto.PlaceResponseDto> placeListToResponseDto(List<Place> places) {
        if ( places == null ) {
            return null;
        }

        List<PlaceDto.PlaceResponseDto> list = new ArrayList<PlaceDto.PlaceResponseDto>( places.size() );
        for ( Place place : places ) {
            list.add( placeToPlaceResponseDto( place ) );
        }

        return list;
    }

    @Override
    public PlaceDto.PlaceResponseDto placeToPlaceResponseDto(Place place) {
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

    protected Member placePostDtoToMember(PlaceDto.PlacePostDto placePostDto) {
        if ( placePostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( placePostDto.getMemberId() );

        return member;
    }

    protected Category placePostDtoToCategory(PlaceDto.PlacePostDto placePostDto) {
        if ( placePostDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryId( placePostDto.getCategoryId() );

        return category;
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
