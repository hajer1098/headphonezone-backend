package com.hajer.Headphone.Controllers.Admin;

import com.hajer.Headphone.Dto.CategoryDto;
import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor

public class AdminCategoryController {
	
	   private final CategoryService service;
	   
	    @PostMapping("/create")
	    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto){

	        return ResponseEntity.ok(service.save(categoryDto));

	    }
	    
	    @GetMapping("")
	     public ResponseEntity<?> getAllCategories(){

			return ResponseEntity.ok(service.findAll());
	    }
	
	

}
