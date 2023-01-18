package main027.server.domain.place.controller;

import lombok.AllArgsConstructor;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.entity.PlaceLikeUser;
import main027.server.domain.place.mapper.PlaceLikeUserMapper;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceLikeService;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final PlaceLikeService placeLikeService;
    private final PlaceMapper placeMapper;
    private final PlaceLikeUserMapper placeLikeUserMapper;


    @TimeTrace
    @PostMapping
    public ResponseEntity postPlace(@Validated @RequestBody PlaceDto.PlacePostDto placePostDto) {
        Place place = placeService.createPlace(placeMapper.placePostDtoToPlace(placePostDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.CREATED);
    }

    @TimeTrace
    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace(@PathVariable("placeId") Long placeId,
                                     @Validated @RequestBody PlaceDto.PlacePatchDto placePatchDto) {
        placePatchDto.setPlaceId(placeId);
        Place place = placeUpdateService.updatePlace(placeMapper.placePatchDtoToPlace(placePatchDto));
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping("/{placeId}")
    public ResponseEntity getPlace(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findPlace(placeId);
        return new ResponseEntity<>(placeMapper.placeToPlaceResponseDto(place), HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping
    public ResponseEntity getPlaces(@RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("PlaceLikeUser"));
        return new ResponseEntity<>(placeMapper.pageToList(placeService.findPlaces(pageable)), HttpStatus.OK);
    }

    @TimeTrace
    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace(@PathVariable("placeId") Long placeId) {
        placeService.deletePlace(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @TimeTrace
    @PostMapping("/likes")
    public ResponseEntity likePlace(@Validated @RequestBody PlaceDto.PlaceLikeDto placeLikeDto) {
        PlaceLikeUser placeLikeUser = placeLikeUserMapper.placeLikeDtoToPlace(placeLikeDto);
        Boolean finalLikeStatus = placeLikeService.changeLikeUserStatus(placeLikeUser);
        return new ResponseEntity(finalLikeStatus, HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping("/likes/{memberId}")
    public ResponseEntity getLikedList(@PathVariable Long memberId,
                                       @RequestParam(defaultValue = "1") Integer page) {
        Page<Place> pagingList = placeLikeService.findPlaceMemberLiked(memberId, PageRequest.of(page-1, 10));
        PlaceDto.PageResponseDto responseDto = placeMapper.pageToList(pagingList);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
