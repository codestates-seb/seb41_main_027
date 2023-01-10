package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public static class PlaceResponseDto {
        private Long placeId;
        private Long memberId;
        private String name;
        private String address;
        private String description;
        private String category;
        private Long likeCount;
        private Long latitude;
        private Long longitude;
    }
}
