package com.hajer.Headphone.Controllers.Admin;


import com.hajer.Headphone.Dto.ProductDto;
import com.hajer.Headphone.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class AdminProductController {
    private final ProductService service;
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody ProductDto productDto){

        return ResponseEntity.ok(service.save(productDto));

    }


    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUser(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) {
        Integer productId = service.Edit(id, productDto);
        return ResponseEntity.ok(productId);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable("productId") Integer id){
        return ResponseEntity.ok((service.findById(id)));

    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductDto>> findAllProductsByName(@PathVariable String name){
        return ResponseEntity.ok(service.findAllProductsByName(name));
    }


}
