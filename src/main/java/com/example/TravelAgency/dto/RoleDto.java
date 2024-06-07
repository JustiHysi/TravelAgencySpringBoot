package com.example.TravelAgency.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RoleDto {
    private long id;
    private String role;
}
