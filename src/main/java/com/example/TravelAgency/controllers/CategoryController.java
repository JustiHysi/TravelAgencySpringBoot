package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dto.CategoryDto;
import com.example.TravelAgency.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
@AllArgsConstructor

public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDto>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>findById(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable("categoryId") Long categoryId,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.update(categoryId,categoryDto));
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<String> delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);

        return ResponseEntity.ok("Category with id; " +categoryId+" was successfully deleted");
    }


}

