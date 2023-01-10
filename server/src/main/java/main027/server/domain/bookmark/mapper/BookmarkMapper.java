package main027.server.domain.bookmark.mapper;

import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "placeId", target = "place.placeId")
    Bookmark PostToEntity(BookmarkDto.Post postDto);
}
