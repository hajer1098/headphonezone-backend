package com.hajer.Headphone.Controllers;


import com.hajer.Headphone.Config.JwtUtils;
import com.hajer.Headphone.Dto.AuthenticationRequest;
import com.hajer.Headphone.Dto.AuthenticationResponse;
import com.hajer.Headphone.Dto.UserDto;
import com.hajer.Headphone.Repositories.UserRepository;
import com.hajer.Headphone.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService service;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> Register(
            @RequestBody UserDto user){
        return ResponseEntity.ok(service.register(user));


    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request){


           return  ResponseEntity.ok(service.authenticate(request));
        
    }


}
