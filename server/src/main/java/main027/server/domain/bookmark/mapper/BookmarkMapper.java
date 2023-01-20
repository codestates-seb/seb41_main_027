package main027.server.domain.bookmark.mapper;

import main027.server.domain.bookmark.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "placeId", target = "place.placeId")
    Bookmark PostToEntity(Long memberId, Long placeId);

}
