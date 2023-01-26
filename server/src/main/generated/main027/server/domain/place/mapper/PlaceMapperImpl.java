package main027.server.domain.place.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.review.entity.Review;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-06T00:55:51+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
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
}
