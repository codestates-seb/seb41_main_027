package main027.server.domain.place.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.dto.PlaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/places")
@Validated
@RequiredArgsConstructor
public class PlaceController {

//    private final PlaceService placeService;
//    private final PlaceMapper placeMapper;

    @PostMapping
    public ResponseEntity postPlace() {
        return ResponseEntity.created(URI.create("/place/1")).build();
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace() {
        PlaceDto.PlaceResponseDto placeResponseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                                   "강남역맥도날드",
                                                                                   "서울시 강남구",
                                                                                   "존나 맛없어요",
                                                                                   "햄버거",
                                                                                   0L,
                                                                                   127L,
                                                                                   37L);
        return ResponseEntity.ok(placeResponseDto);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getPlace() {
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L, "강남역맥도날드","서울시 강남구","존나 맛없어요");
        return ResponseEntity.ok(placePostDto);
    }

    @GetMapping
    public ResponseEntity getPlaces() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace() {
        return ResponseEntity.noContent().build();
    }
}
