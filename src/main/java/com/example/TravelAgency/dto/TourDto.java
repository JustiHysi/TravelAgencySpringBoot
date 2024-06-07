package com.example.TravelAgency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TourDto {
    private Long id;
    private String name;
    private String description;
    private int duration;
    private int price;
    private Set<ReviewDto> reviewDtoSet;
    private Long categoryId;

}
