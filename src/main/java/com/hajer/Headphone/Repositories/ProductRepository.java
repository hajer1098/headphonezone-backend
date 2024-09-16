package com.hajer.Headphone.Repositories;

import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findAllByNameContaining(String title);
}
