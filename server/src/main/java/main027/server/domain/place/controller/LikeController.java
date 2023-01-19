package main027.server.domain.place.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.entity.PlaceLikeUser;
import main027.server.domain.place.mapper.PlaceLikeUserMapper;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceLikeService;
import main027.server.global.aop.logging.MemberHolder;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final PlaceLikeService placeLikeService;
    private final PlaceLikeUserMapper placeLikeUserMapper;
    private final PlaceMapper placeMapper;
    private final MemberHolder memberHolder;

    @TimeTrace
    @PostMapping("/{placeId}")
    public ResponseEntity likePlace(@PathVariable Long placeId) {
        PlaceLikeUser placeLikeUser = placeLikeUserMapper.placeLikeDtoToPlace(memberHolder.getMemberId(), placeId);
        Boolean finalLikeStatus = placeLikeService.changeLikeUserStatus(placeLikeUser);
        return new ResponseEntity(finalLikeStatus, HttpStatus.OK);
    }

    @TimeTrace
    @GetMapping()
    public ResponseEntity getLikedList(@RequestParam(defaultValue = "1") Integer page) {
        Page<Place> pagingList = placeLikeService.findPlaceMemberLiked(memberHolder.getMemberId(),
                                                                       PageRequest.of(page-1, 10));
        PlaceDto.PageResponseDto responseDto = placeMapper.pageToList(pagingList, memberHolder.getMemberId());
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
