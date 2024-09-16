package com.hajer.Headphone.Implements;

import com.hajer.Headphone.Dto.ProfileDto;
import com.hajer.Headphone.Models.Profile;
import com.hajer.Headphone.Repositories.ProfileRepostitory;
import com.hajer.Headphone.Services.ProfileService;
import com.hajer.Headphone.Validators.Objectsvalidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepostitory repository;
    private final Objectsvalidator<ProfileDto> validator;
    @Override
    public Integer save(ProfileDto dto) {
        //valide object
        validator.validate(dto);
        //transform object to entity
        Profile profile= ProfileDto.toEntity(dto);
        //renvoyer object by id
        return repository.save(profile).getId();
    }

    @Override
    public List<ProfileDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ProfileDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDto findById(Integer id) {
        return repository.findById(id)
                .map(ProfileDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No profile was found with the provided ID"+ id));

    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
