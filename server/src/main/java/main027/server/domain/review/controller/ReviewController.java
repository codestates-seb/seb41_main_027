package main027.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.mapper.ReviewMapper;
import main027.server.domain.review.service.ReviewService;
import main027.server.domain.review.verifier.ReviewVerifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewVerifier reviewVerifier;
    private final ReviewMapper mapper;

    /**
     * 장소에 리뷰를 등록하는 컨트롤러
     */
    @PostMapping
    public ResponseEntity post(@Validated @RequestBody ReviewDto.Post postDto) {
        ReviewDto.Response response = mapper.entityToResponse(reviewService.save(mapper.PostToEntity(postDto)));

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    /**
     * 장소에 등록되어 있는 리뷰 목록을 페이징처리로 가져오는 컨트롤러
     *
     * @param placeId 리뷰 목록을 가져올 장소의 id
     * @param page    가져오고 싶은 페이지 (default: 1)
     * @return {@link ReviewDto.ListResponse}
     */
    @GetMapping("/{placeId}")
    public ResponseEntity getPlaceReviews(@PathVariable Long placeId,
                                          @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        ReviewDto.ListResponse response = mapper.pageToList(reviewService.findReviews(placeId, pageable));

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 사용하지 않는 로직
     */
    /* @GetMapping("/{reviewId}")
    public ResponseEntity getReview(@PathVariable Long reviewId) {
        ReviewDto.Response response = mapper.entityToResponse(reviewVerifier.findVerifiedReview(reviewId));

        return new ResponseEntity(response, HttpStatus.OK);
    } */
}
