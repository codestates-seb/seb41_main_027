package main027.server.domain.place.mapper;

import main027.server.domain.place.entity.PlaceLikeUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaceLikeUserMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "placeId", target = "place.placeId")
    PlaceLikeUser placeLikeDtoToPlace(Long memberId, Long placeId);

}
