package main027.server.domain.place.controller;

import lombok.AllArgsConstructor;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/places")
@Validated
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceUpdateService placeUpdateService;
    private final PlaceMapper placeMapper;

    @PostMapping
    public ResponseEntity postPlace(@Validated @RequestBody PlaceDto.PlacePostDto placePostDto) {
        Place place = placeService.createPlace(placeMapper.placePostDtoToPlace(placePostDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.CREATED);
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace(@PathVariable("placeId") Long placeId,
                                     @Validated @RequestBody PlaceDto.PlacePatchDto placePatchDto) {
        placePatchDto.setPlaceId(placeId);
        Place place = placeUpdateService.updatePlace(placeMapper.placePatchDtoToPlace(placePatchDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getPlace(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findPlace(placeId);
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPlaces(@RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        return new ResponseEntity<>(placeMapper.pageToList(placeService.findPlaces(pageable)),HttpStatus.OK);
    }

//    @GetMapping("/{categoryId}")
//    public ResponseEntity getPlaceByCategory(R)

    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace(@PathVariable("placeId") Long placeId) {
        placeService.deletePlace(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
