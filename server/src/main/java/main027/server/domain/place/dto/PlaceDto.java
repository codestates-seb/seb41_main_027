package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main027.server.global.BaseTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlaceDto {

    @Data
    @AllArgsConstructor
    public class PlacePostDto {
        @NotNull
        private Long userId;
        @NotBlank
        private String name;
        @NotBlank
        private String address;
        @NotBlank
        private String description;
    }

    public class PlacePatchDto {

    }

    @Data
    public class PlaceResponseDto extends BaseTime {
        private Long placeId;
        private String name;
        private String address;
        private String description;
        private String category;
        private Long likeCount;
        private Long latitude;
        private Long longitude;
    }
}
