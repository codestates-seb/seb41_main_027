package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main027.server.domain.place.entity.Category;
import main027.server.global.audit.BaseTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PlaceDto extends BaseTime {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlacePostDto {
        @NotNull
        private Long memberId;
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
        private Long memberId;
        private Long placeId;
    }

    @Data
    @AllArgsConstructor
    public static class PlaceResponseDto {
        private Long placeId;
        private Long memberId;
        private String name;
        private String address;
        private String description;
        private List placeLikeUserList;
        private Boolean isBookMarked;
        private Long kakaoId;
        private String category;
        private String latitude;
        private String longitude;
    }

    @Data
    public static class PageResponseDto {
        List<PlaceResponseDto> placeList;
        private Long totalPages;
        private Long presentPage;
        private Long totalElements;
    }
}
