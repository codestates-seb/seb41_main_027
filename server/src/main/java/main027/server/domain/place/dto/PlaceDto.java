package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main027.server.global.audit.BaseTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class PlaceDto extends BaseTime {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlacePostDto {
        @NotBlank
        private String name;
        @NotBlank
        private String address;
        @NotBlank
        private String description;
        private Long kakaoId;
        private Long categoryId;
        private String latitude;
        private String longitude;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlacePatchDto {

        private  Long placeId;
        @NotBlank
        private String description;

    }

    @Data
    @AllArgsConstructor
    public static class PlaceLikeDto {
        @NotNull
        private Long memberId;
        @NotNull
        private Long placeId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchResponseDto {
        private Long placeId;
        private String name;
        private String address;
        private String category;
        private String latitude;
        private String longitude;
        private String description;
        private int likeCount;
    }

    @Data
    public static class SearchPageResponseDto{
        List<SearchResponseDto> placeList;
        private Long totalPages;
        private Long presentPage;
        private Long totalElements;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlaceResponseDto {
        private Long placeId;
        private Long memberId;
        private String name;
        private String address;
        private String description;
        private int likeCount;
        private Boolean isBookMarked;
        private Boolean isLiked;
        private Long kakaoId;
        private String category;
        private String latitude;
        private String longitude;
        private LocalDateTime createdAt;
    }

    @Data
    public static class PageResponseDto {
        List<PlaceResponseDto> placeList;
        private Long totalPages;
        private Long presentPage;
        private Long totalElements;
    }
}
