package com.hajer.Headphone.Services;

import com.hajer.Headphone.Dto.AuthenticationRequest;
import com.hajer.Headphone.Dto.AuthenticationResponse;
import com.hajer.Headphone.Dto.UserDto;
import com.hajer.Headphone.Dto.changePasswordRequest;

import java.security.Principal;

public interface UserService extends AbstractService<UserDto> {

   Integer validateAccount(Integer id);
   Integer invalidateAccount(Integer id);

    Integer Edit(Integer id,UserDto user);
    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void changePassword(changePasswordRequest request, Principal connectedUser);
}
