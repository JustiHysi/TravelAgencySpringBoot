package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dto.TourDto;
import com.example.TravelAgency.services.TourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")

public class TourController {
    private TourService tourService;
    @PostMapping("/save")
    public ResponseEntity<TourDto> save(@RequestBody TourDto tourDto) {
        return new ResponseEntity<>(tourService.save(tourDto), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TourDto>> findAll() {
        return ResponseEntity.ok(tourService.findAll());
    }

    @GetMapping("/view/{tourId}")
    public ResponseEntity<TourDto>findById(@PathVariable("tourId") long tourId){
        return ResponseEntity.ok(tourService.findById(tourId));
    }
    @PutMapping ("/update/{tourId}")
    public ResponseEntity<TourDto> updateTour(@RequestBody TourDto tourDto,
                                              @PathVariable("tourId") long tourId) {
        return ResponseEntity.ok(tourService.update(tourDto, tourId));
    }
    @DeleteMapping("/{tourId}")
    public ResponseEntity<String> deleteTour(@PathVariable("tourId") long tourId) {
        tourService.delete(tourId);
        return ResponseEntity.ok("Tour with id "+tourId+" has been deleted");
    }
    @GetMapping("/findByName/{tourName}")
    public ResponseEntity<TourDto>findByName(@PathVariable("tourName") String tourName){
        return ResponseEntity.ok(tourService.findByName(tourName));

    }
}
