package com.example.TravelAgency.services;

import com.example.TravelAgency.dto.ReviewDto;
import com.example.TravelAgency.entities.Review;
import com.example.TravelAgency.entities.Tour;
import com.example.TravelAgency.mapper.ReviewMapper;
import com.example.TravelAgency.repositories.ReviewRepository;
import com.example.TravelAgency.repositories.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService  {
    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private TourRepository tourRepository;
    public ReviewDto save(ReviewDto reviewDto, long tourId){
        Tour excistingTour=tourRepository.findById(tourId)
                .orElseThrow(()-> (
                        new RuntimeException("Tour with id:" + tourId + "was not found.")
                ));
        Review review= reviewMapper.mapToEntity(reviewDto);
        review.setTour(excistingTour);
        Review savedReview=reviewRepository.save(review);
        return reviewMapper.mapToDto(savedReview);

    }
    public List<ReviewDto> findAll(long tourId){
        Tour excistingTour=tourRepository.findById(tourId)
                .orElseThrow(()->(new RuntimeException("Tour with id"+ tourId+"was not found")
                ));
        List<Review> reviewList=reviewRepository.findByTourId(tourId);
        return reviewList.stream()
                .map(review -> reviewMapper.mapToDto(review))
                .collect(Collectors.toList());

    }
    public ReviewDto findById(long tourId,long reviewId){
        Tour excistingTour=tourRepository.findById(tourId)
                .orElseThrow(()-> (
                        new RuntimeException("Tour with id:" + tourId + "was not found.")
                ));
        Review existingReview=reviewRepository.findById(reviewId)
                .orElseThrow(()-> (
                        new RuntimeException("Review with id:"+ reviewId+ "was not found"))
                );
        return reviewMapper.mapToDto(existingReview);
    }
    public ReviewDto update(ReviewDto reviewDto,long tourId,long reviewId){
        Tour excistingTour=tourRepository.findById(tourId)
                .orElseThrow(()-> (
                        new RuntimeException("Tour with id:" + tourId + "was not found.")
                ));
        Review existingReview=reviewRepository.findById(reviewId)
                .orElseThrow(()-> (
                        new RuntimeException("Review with id:"+ reviewId+ "was not found"))
                );
        if(!((excistingTour.getId())==(existingReview.getTour().getId()))){
            throw new RuntimeException("Review with id:"+ reviewId+"does not correspond to tour with id:"+tourId);
        }
        Review review=new Review();
        review.setId(reviewId);
        review.setDescription(reviewDto.getDescription());
        Review savedReview= reviewRepository.save(review);
        return reviewMapper.mapToDto(savedReview);
    }
    public void delete(long tourId,long reviewId){
        Tour excistingTour=tourRepository.findById(tourId)
                .orElseThrow(()-> (
                        new RuntimeException("Tour with id:" + tourId + "was not found.")
                ));
        Review existingReview=reviewRepository.findById(reviewId)
                .orElseThrow(()-> (
                        new RuntimeException("Review with id:"+ reviewId+ "was not found"))
                );
        if(!((excistingTour.getId())==(existingReview.getTour().getId()))){
            throw new RuntimeException("Review with id:"+ reviewId+"does not correspond to tour with id:"+tourId);
        }
        reviewRepository.delete(existingReview);
    }
}


