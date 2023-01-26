package main027.server.domain.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main027.server.domain.place.dto.PlaceDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BookmarkDto {

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private Long placeId;
    }

    /**
     * 사용자가 저장한 북마크 리스트를 제공하기 위한 ResponseDto
     *
     * @totalPage 전체 페이지 수
     * @presentPage 현재 페이지
     * @totalElements 회원이 북마킹한 전체 Place 개수
     */
    @Setter
    @Getter
    public static class Response {

        private List<PlaceDto.PlaceResponseDto> placeList;
        private Long totalPages;
        private Long presentPage;
        private Long totalElements;
    }
}
