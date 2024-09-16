package com.hajer.Headphone.Services;

import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Models.Product;

import java.util.List;


public interface ProductService  extends AbstractService<ProductDto> {


    Integer Edit(Integer id, ProductDto productDto);
    List<ProductDto> findAllProductsByName(String name);

    String SearchByCategory(String category);


}

