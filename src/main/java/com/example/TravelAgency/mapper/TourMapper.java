package com.example.TravelAgency.mapper;

import com.example.TravelAgency.dto.ReviewDto;
import com.example.TravelAgency.dto.TourDto;
import com.example.TravelAgency.entities.Review;
import com.example.TravelAgency.entities.Tour;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Component
@AllArgsConstructor
public class TourMapper {
    private ReviewMapper reviewMapper;

    public Tour mapToEntity(TourDto tourDto){
        Tour tour = new Tour();
        tour.setId(tourDto.getId());
        tour.setName(tourDto.getName());
        tour.setDescription(tourDto.getDescription());
        tour.setDuration(tourDto.getDuration());
        tour.setPrice(tourDto.getPrice());
        return tour;
    }
    public TourDto mapToDto(Tour tour){
        TourDto tourDto = new TourDto();
        tourDto.setId(tour.getId());
        tourDto.setName(tour.getName());
        tourDto.setDescription(tour.getDescription());
        tourDto.setDuration(tour.getDuration());
        tourDto.setPrice(tour.getPrice());

        Set<Review> reviewSet = tour.getReviews();
        Set<ReviewDto> reviewDtoset=new HashSet<>();
        if (reviewSet != null) {
            for (Review review:reviewSet){
                reviewDtoset.add(reviewMapper.mapToDto(review));}
        }else {
            // Handle the case where reviewSet is null, for example, by setting an empty set or logging a message.
            // You can decide based on your application's requirements.
            reviewDtoset = Collections.emptySet(); // or reviewDtoset = new HashSet<>(); depending on your preference
        }
        tourDto.setReviewDtoSet(reviewDtoset);
        tourDto.setCategoryId(tour.getCategory().getId());

        return tourDto;
    }
}
