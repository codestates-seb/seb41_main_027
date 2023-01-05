package main027.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public void post(@RequestBody ReviewDto.Post postDto) {
        
    }
}
