package main027.server.domain.bookmark.mapper;

import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    Bookmark PostToEntity(BookmarkDto.Post postDto);
}
