package main027.server.domain.place.mapper;

import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place placePostDtoToPlace(PlaceDto.PlacePostDto placePostDto);

    Place placePatchDtoToPlace(PlaceDto.PlacePatchDto placePatchDto);

    @Mapping(source = "category.name", target = "category")
    PlaceDto.PlaceResponseDto placeToPlaceResponseDto(Place place);

    List<PlaceDto.PlaceResponseDto> placeListToResponseDto(List<Place> places);
}
