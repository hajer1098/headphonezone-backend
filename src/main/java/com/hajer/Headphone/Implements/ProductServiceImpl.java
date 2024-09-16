package com.hajer.Headphone.Implements;


import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Models.Category;
import com.hajer.Headphone.Models.Product;
import com.hajer.Headphone.Repositories.CategoryRepository;
import com.hajer.Headphone.Repositories.ProductRepository;
import com.hajer.Headphone.Services.ProductService;

import com.hajer.Headphone.Validators.Objectsvalidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {

    private ProductRepository repository;
    
    private CategoryRepository categoryRepository;
    @Autowired
    private Objectsvalidator<ProductDto> validator;

    @Override
        public Integer save(ProductDto productDto) {
        //validate object
        validator.validate(productDto);
        //transform object to entity
        Product product=new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        
        Category category=categoryRepository.findById(product.getCategory().getId()).orElseThrow();
        product.setCategory(category);
        return this.repository.save(product).getId();
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Integer id) {
        return repository.findById(id)
                .map(ProductDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No product found"+ id));
    }

    @Override
    public void delete(Integer id) {
        this.repository.deleteById(id);

    }

    @Override
    public Integer Edit(Integer id, ProductDto productDto) {
        Product existingProduct=
                this.repository.findById(id).orElseThrow(()-> new EntityNotFoundException("No product found"+ id));
        // Validate the DTO
        validator.validate(productDto);

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
         return this.repository.save(existingProduct).getId();

    }

    @Override
    public List<ProductDto> findAllProductsByName(String name) {
        return repository.findAllByNameContaining(name)
                .stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());

    }




    @Override
    public String SearchByCategory(String category) {
        return "";
    }
}
