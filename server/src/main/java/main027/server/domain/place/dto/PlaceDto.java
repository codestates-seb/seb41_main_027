package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    public class PlaceResponseDto {
        private Long placeId;
        private String name;
        private String address;
        private String description;
        private String category;
        private Long likeCount;
        private Long mapId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
