package main027.server.domain.place.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/places")
@Validated
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceUpdateService placeUpdateService;
    private final PlaceMapper placeMapper;
    private final MemberHolder memberHolder;

    @TimeTrace
    @PostMapping
    public ResponseEntity postPlace(@Validated @RequestBody PlaceDto.PlacePostDto placePostDto) {
        Place place = placeService.createPlace(
                placeMapper.placePostDtoToPlace(placePostDto, memberHolder.getMemberId()));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place, memberHolder.getMemberId()),
                                    HttpStatus.CREATED);
    }

    @TimeTrace
    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace(@PathVariable("placeId") Long placeId,
                                     @Validated @RequestBody PlaceDto.PlacePatchDto placePatchDto) {
        placePatchDto.setPlaceId(placeId);
        Place place = placeUpdateService.updatePlace(placeMapper.placePatchDtoToPlace(placePatchDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place, memberHolder.getMemberId()),
                                    HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping("/{placeId}")
    public ResponseEntity getPlace(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findPlace(placeId);

        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place, memberHolder.getMemberId()),
                                    HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPlaces(@RequestParam(value = "id", required = false) Long categoryId,
                                    @RequestParam(value = "sortby", defaultValue = "") String sortBy,
                                    @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return new ResponseEntity(placeMapper.pageToList(placeService.findPlaces(pageable, categoryId, sortBy),
                                                         memberHolder.getMemberId()),
                                  HttpStatus.OK);
    }

    /*  @TimeTrace
     @GetMapping("/likes")
     public ResponseEntity getPlacesByLikes(@RequestParam(defaultValue = "1") Integer page) {
         Pageable pageable = PageRequest.of(page - 1, 10);
         return new ResponseEntity<>(placeMapper.pageToList(placeService.findPlacesByLikes(pageable)), HttpStatus.OK);
     }

     @TimeTrace
     @GetMapping("/createdAt")
     public ResponseEntity getPlacesByCreated(@RequestParam(defaultValue = "1") Integer page) {
         Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
         Pageable pageable = PageRequest.of(page - 1, 10, sort);
         return new ResponseEntity<>(placeMapper.pageToList(placeService.findPlaces(pageable)), HttpStatus.OK);
     }

     @TimeTrace
     @GetMapping("/category/{categoryId}")
     public ResponseEntity getPlacesByCategory(@PathVariable("categoryId") Long categoryId ,@RequestParam
     (defaultValue = "1") Integer page) {
         Pageable pageable = PageRequest.of(page - 1, 10);
         return new ResponseEntity<>(placeMapper.pageToList(placeService.findPlacesByCategory(pageable,categoryId)),
         HttpStatus.OK);
     }
  */
    // TODO: 삭제하려는 place의 작성자가 memberId와 같은지 확인한 후 지우는 로직 필요
    @TimeTrace
    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace(@PathVariable("placeId") Long placeId) {
        placeService.deletePlace(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
