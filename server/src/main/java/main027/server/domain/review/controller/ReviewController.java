package main027.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.mapper.ReviewMapper;
import main027.server.domain.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @PostMapping
    public ResponseEntity post(@Validated @RequestBody ReviewDto.Post postDto) {
        Review review = mapper.PostToEntity(postDto);
        ReviewDto.Response response = mapper.entityToResponse(reviewService.save(review));

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable Long reviewId) {
        return reviewService.findReview(reviewId);
    }
}
