package main027.server.domain.place.mapper;

import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import org.mapstruct.Mapper;


@Mapper
public interface PlaceMapper {
    Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto);

    Place placePatchDtoToPlace(PlaceDto.PlacePatchDto placePatchDto);

    Place placeToPlaceResponseDto(Place place);
}
