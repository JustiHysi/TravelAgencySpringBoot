package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dto.ReviewDto;
import com.example.TravelAgency.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")

public class ReviewController {
    private ReviewService reviewService;
    @PostMapping("/save/{tourId}")
    public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto reviewDto, @PathVariable("tourId") Long tourId){
        return new ResponseEntity<>(reviewService.save(reviewDto,tourId), HttpStatus.CREATED);
    }
    @GetMapping("/findAll/{tourId}")
    public ResponseEntity<List<ReviewDto>> findAll(@PathVariable("tourId") Long tourId){
        return  ResponseEntity.ok(reviewService.findAll(tourId));
    }
    @GetMapping("/findById/{tourId}/{reviewId}")
    public ResponseEntity<ReviewDto> findById(@PathVariable("tourId")long tourId,@PathVariable("reviewId") long reviewId){
        return new ResponseEntity<>(reviewService.findById(tourId, reviewId),HttpStatus.OK);
    }
    @PutMapping("/update/{tourId}/{reviewId}")
    public ResponseEntity<ReviewDto> update(@RequestBody ReviewDto reviewDto,@PathVariable("tourId") long tourId,@PathVariable("reviewId") long reviewId){
        return ResponseEntity.ok(reviewService.update(reviewDto,tourId,reviewId));
    }
    @DeleteMapping("/delete/{tourId}/{reviewId}")
    public  ResponseEntity<String> delete(@PathVariable("tourId") long tourId, @PathVariable("reviewId") long reviewId){
        reviewService.delete(tourId,reviewId);
        return ResponseEntity.ok("Review with id:"+ reviewId+"was deleted");
    }
}
