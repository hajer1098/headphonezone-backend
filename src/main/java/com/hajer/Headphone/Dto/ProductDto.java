package com.hajer.Headphone.Dto;


import com.hajer.Headphone.Models.Product;
import com.hajer.Headphone.Models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {
    private Integer id;

    @NonNull
    @NotEmpty(message = "name is empty !")
    private String name;

    @NonNull
    @NotEmpty(message = "description is empty !")
    private String description;

    @NonNull
    @NotEmpty(message = "price is empty!")
    private float price;

    @NonNull
    private byte[] image;


    private Integer CategoryId;

    private String  CategoryName;




    public  static  ProductDto fromEntity(Product product){
        //null check
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .CategoryId(product.getCategory().getId())
                .CategoryName(product.getCategory().getName())
                .build();
    }

    public  static  Product toEntity(ProductDto productDto){
        //null check
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .build();
    }

}
