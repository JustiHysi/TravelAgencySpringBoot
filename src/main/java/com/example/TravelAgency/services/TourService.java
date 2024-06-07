package com.example.TravelAgency.services;

import com.example.TravelAgency.dto.TourDto;
import com.example.TravelAgency.entities.Category;
import com.example.TravelAgency.entities.Tour;
import com.example.TravelAgency.mapper.TourMapper;
import com.example.TravelAgency.repositories.CategoryRepository;
import com.example.TravelAgency.repositories.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourService {
    private TourRepository tourRepository;
    private TourMapper tourMapper;
    private CategoryRepository categoryRepository;


    public List<TourDto> findAll() {
        List<Tour> tourList = tourRepository.findAll();

        return tourList.stream().map(tour -> tourMapper.mapToDto(tour)).collect(Collectors.toList());
    }

    public TourDto findById(long tourId) {
        Optional<Tour> foundTour = tourRepository.findById(tourId);
        if (foundTour.isPresent()) {
            foundTour.get();
        } else throw new RuntimeException("This tour does not exist");


        return tourMapper.mapToDto(foundTour.get());
    }

    public void delete(long tourId) {
        Tour foundTour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("This tour does not exist"));
        tourRepository.delete(foundTour);
    }

    public TourDto save(TourDto tourDto) {
        Category existingCategory = categoryRepository.findById(tourDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category with id " +
                        tourDto.getCategoryId() + " not found"));

        Tour tour = tourMapper.mapToEntity(tourDto);
        tour.setCategory(existingCategory);

        Tour savedTour = tourRepository.save(tour);


        return tourMapper.mapToDto(savedTour);
    }
    public TourDto update(TourDto tourDto, long tourId){
        Category existingCategory = categoryRepository.findById(tourDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category with id " + tourDto.getCategoryId() + " not found"));
        Tour foundTour = tourRepository.findById(tourId).orElseThrow(()-> new RuntimeException("This tour does not exist"));
        foundTour.setId(tourId);
        foundTour.setName(tourDto.getName());
        foundTour.setDescription(tourDto.getDescription());
        foundTour.setDuration(tourDto.getDuration());
        foundTour.setPrice(tourDto.getPrice());
        foundTour.setCategory(existingCategory);
        Tour savedTour = tourRepository.save(foundTour);

        return tourMapper.mapToDto(savedTour);
    }
    public TourDto findByName(String tourName){
        Optional<Tour> foundTour = tourRepository.findByName(tourName);
        if (foundTour.isPresent()){
            foundTour.get();
        }
        else  throw new RuntimeException("Does not exist a tour with "+tourName+" name");


        return tourMapper.mapToDto(foundTour.get());
    }

}
