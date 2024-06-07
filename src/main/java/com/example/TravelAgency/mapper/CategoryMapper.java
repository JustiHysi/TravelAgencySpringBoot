package com.example.TravelAgency.mapper;

import com.example.TravelAgency.dto.CategoryDto;
import com.example.TravelAgency.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        return categoryDto;
    }
}
