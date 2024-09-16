package com.hajer.Headphone.Implements;

import com.hajer.Headphone.Dto.CategoryDto;
import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Models.Category;
import com.hajer.Headphone.Repositories.CategoryRepository;
import com.hajer.Headphone.Services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepo;
    @Override
    public Integer save(CategoryDto dto) {
        Category category=new Category();
        category.setName(dto.getName());
        category.setName(dto.getDescription());
        return categoryRepo.save(category).getId();
    }

    @Override
    public List<CategoryDto> findAll() {

        return this.categoryRepo.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
