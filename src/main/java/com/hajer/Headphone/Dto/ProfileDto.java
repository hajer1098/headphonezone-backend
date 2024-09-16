package com.hajer.Headphone.Dto;

import com.hajer.Headphone.Models.Profile;
import com.hajer.Headphone.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProfileDto  {

    private Integer id;

    private Date dateOfBirth;

    private String phoneNumber;

    private String description;


    private Integer userId;




    public  static  ProfileDto fromEntity(Profile profile){
        return ProfileDto.builder()
                .id(profile.getId())
                .dateOfBirth(profile.getDateOfBirth())
                .description(profile.getDescription())
                .phoneNumber(profile.getPhoneNumber())
                .userId(profile.getUser().getId())
                .build();
    }

    public  static Profile toEntity(ProfileDto profile){
        return Profile.builder()
                .id(profile.getId())
                .dateOfBirth(profile.getDateOfBirth())
                .description(profile.getDescription())
                .phoneNumber(profile.getPhoneNumber())
                .user(User.builder().id(profile.getUserId()).build())
                .build();
    }
}
