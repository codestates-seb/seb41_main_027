package main027.server.domain.place.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@Validated
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @PostMapping
    public ResponseEntity postPlace(@Validated @RequestBody PlaceDto.PlacePostDto placePostDto) {
        placePostDto.setMemberId(placePostDto.getMemberId());
        Place place = placeService.createPlace(placeMapper.placePostDtoToPlace(placePostDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.CREATED);
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace(@PathVariable("placeId") Long placeId,
                                     @Validated @RequestBody PlaceDto.PlacePatchDto placePatchDto) {
        placePatchDto.setPlaceId(placeId);
        Place place = placeService.updatePlace(placeMapper.placePatchDtoToPlace(placePatchDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getPlace(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findPlace(placeId);
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPlaces(@RequestParam(defaultValue = "-1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size) {
        List<Place> places;
        if (page > 0 && size > 0) {
            Page<Place> placePage = placeService.findPlaces(
                    PageRequest.of(page - 1, size, Sort.by("likeCount").descending()));
            places = placePage.getContent();

        } else {
            places = placeService.findAll();
        }
        return new ResponseEntity<>(placeMapper.placeListToResponseDto(places),HttpStatus.OK);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace(@PathVariable("placeId") Long placeId) {
        placeService.deletePlace(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
