package com.hajer.Headphone.Dto;

import com.hajer.Headphone.Models.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

  private Integer id;
  @NonNull

  @NotEmpty(message = "fistname is empty !")
    private String firstname;


  @NonNull
   
  @NotEmpty(message = "lastname is empty!")
    private String lastname;


    @NonNull
    @NotEmpty(message = "email is empty!")
    @Email(message="email not valid")
    private  String email;

  @NonNull
  @NotEmpty(message = "paswword is empty !")
   @Size(min = 8,max = 16,message = "password must be betwen 8 and 16 !")
    private String password;

  private boolean active;

  private String adresse;

    public  static  UserDto fromEntity(User user){
        //null check
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isEnabled())
                .adresse(user.getAdresse())
                .build();
    }

    public  static  User toEntity(UserDto user){
        //null check
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .adresse(user.getAdresse())

                .build();
    }
}
