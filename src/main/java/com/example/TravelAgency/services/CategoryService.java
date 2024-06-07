package com.example.TravelAgency.services;

import com.example.TravelAgency.dto.CategoryDto;
import com.example.TravelAgency.entities.Category;
import com.example.TravelAgency.mapper.CategoryMapper;
import com.example.TravelAgency.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryDto save(CategoryDto categoryDto){
//        Category category=new Category();
//        category.setId(categoryDto.getId());
//        category.setCategory(categoryDto.getCategory());

        Category category=categoryMapper.mapToEntity(categoryDto);
        Category savedcategory=categoryRepository.save(category);
        return categoryMapper.mapToDto(savedcategory);
    }

    public List<CategoryDto> findAll(){
        List<Category> categoryList=categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDtoList.add(categoryMapper.mapToDto(category));
        }
        return categoryDtoList;
    }

    public CategoryDto findById(long categoryId){
        Optional<Category> foundCategory=categoryRepository.findById(categoryId);
        if(foundCategory.isPresent()){
            foundCategory.get();
        }else throw new RuntimeException("Category with id : " + categoryId + "not found");

        return categoryMapper.mapToDto(foundCategory.get());
    }

    public CategoryDto update(long categoryId,CategoryDto categoryDto){
        Optional<Category> foundCategory =categoryRepository.findById(categoryId);
        if(!foundCategory.isPresent()){
            throw new RuntimeException("Category with id :" + categoryId + "not found");
        }else foundCategory.get();
        foundCategory.get().setId(categoryId);
        foundCategory.get().setName(categoryDto.getName());
        foundCategory.get().setDescription(categoryDto.getDescription());


        Category savedCategory= categoryRepository.save(foundCategory.get());

        return categoryMapper.mapToDto(savedCategory);
    }

    public void delete (long categoryId){
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category with id: " + categoryId + " not found"));

        categoryRepository.delete(existingCategory);
    }



}



















