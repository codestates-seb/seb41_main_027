package main027.server.domain.place.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.review.entity.Review;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T21:52:35+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class PlaceMapperImpl implements PlaceMapper {

    @Override
    public Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto) {
        if ( placePostDto == null ) {
            return null;
        }

        Place place = new Place();

        place.setName( placePostDto.getName() );
        place.setAddress( placePostDto.getAddress() );
        place.setDescription( placePostDto.getDescription() );

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
    public Place placeToPlaceResponseDto(Place place) {
        if ( place == null ) {
            return null;
        }

        Place place1 = new Place();

        place1.setCreatedAt( place.getCreatedAt() );
        place1.setModifiedAt( place.getModifiedAt() );
        place1.setPlaceId( place.getPlaceId() );
        place1.setName( place.getName() );
        place1.setAddress( place.getAddress() );
        place1.setDescription( place.getDescription() );
        place1.setCategory( place.getCategory() );
        place1.setLikeCount( place.getLikeCount() );
        place1.setKakaoId( place.getKakaoId() );
        place1.setLatitude( place.getLatitude() );
        place1.setLongitude( place.getLongitude() );
        place1.setMember( place.getMember() );
        List<Review> list = place.getReviews();
        if ( list != null ) {
            place1.setReviews( new ArrayList<Review>( list ) );
        }

        return place1;
    }

    @Override
    public List<PlaceDto.PlaceResponseDto> placeListToResponseDto(List<Place> places) {
        if ( places == null ) {
            return null;
        }

        List<PlaceDto.PlaceResponseDto> list = new ArrayList<PlaceDto.PlaceResponseDto>( places.size() );
        for ( Place place : places ) {
            list.add( placeToPlaceResponseDto1( place ) );
        }

        return list;
    }

    protected PlaceDto.PlaceResponseDto placeToPlaceResponseDto1(Place place) {
        if ( place == null ) {
            return null;
        }

        Long placeId = null;
        String name = null;
        String address = null;
        String description = null;
        String category = null;
        Long likeCount = null;
        Long latitude = null;
        Long longitude = null;

        placeId = place.getPlaceId();
        name = place.getName();
        address = place.getAddress();
        description = place.getDescription();
        category = place.getCategory();
        likeCount = place.getLikeCount();
        latitude = place.getLatitude();
        longitude = place.getLongitude();

        Long memberId = null;

        PlaceDto.PlaceResponseDto placeResponseDto = new PlaceDto.PlaceResponseDto( placeId, memberId, name, address, description, category, likeCount, latitude, longitude );

        return placeResponseDto;
    }
}
