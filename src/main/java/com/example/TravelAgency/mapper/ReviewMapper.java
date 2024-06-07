package com.example.TravelAgency.mapper;

import com.example.TravelAgency.dto.ReviewDto;
import com.example.TravelAgency.entities.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review mapToEntity(ReviewDto reviewDto){
        Review review=new Review();
        review.setId(reviewDto.getId());
        review.setDescription(reviewDto.getDescription());
        return review;
    }
    public ReviewDto mapToDto(Review review){
        ReviewDto reviewDto=new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        return reviewDto;
    }
}
