package com.hajer.Headphone.Dto;

import com.hajer.Headphone.Models.Category;
import com.hajer.Headphone.Models.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    @NonNull
    @NotEmpty(message = "name is empty !")
    private String name;

    @NonNull
    @NotEmpty(message = "category is empty !")
    private String description;

    public  static  CategoryDto fromEntity(Category category){
        //null check
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public  static  Product toEntity(ProductDto productDto){
        //null check
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .image(productDto.getImage())
                .build();
    }
}
