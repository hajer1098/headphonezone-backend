package com.hajer.Headphone.Implements;

import com.hajer.Headphone.Config.JwtUtils;
import com.hajer.Headphone.Dto.*;
import com.hajer.Headphone.Enum.OrderStatus;
import com.hajer.Headphone.Models.Order;
import com.hajer.Headphone.Models.Role;
import com.hajer.Headphone.Models.User;
import com.hajer.Headphone.Repositories.OrderRepository;
import com.hajer.Headphone.Repositories.RoleRepositroy;
import com.hajer.Headphone.Repositories.UserRepository;
import com.hajer.Headphone.Services.UserService;
import com.hajer.Headphone.Validators.Objectsvalidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final RoleRepositroy roleRepositroy;
    private static final String ROLE_USER="ROLE_USER";

    @Autowired
    private  Objectsvalidator<UserDto> validator;
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public Integer save(UserDto dto) {
        //valide object
        validator.validate(dto);


        //transform object to entity
        User user=UserDto.toEntity(dto);
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );

        user.setActive(false);
        // Check if the password needs to be updated
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            // Encode the new password
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            // If no new password is provided, retain the existing password
            user.setPassword(user.getPassword());
        }
        Order order =new Order();
        order.setAmount(0);
        order.setTotalAmount(0);
        order.setDiscount(0);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
     
        //renvoyer object by id
        return repository.save(user).getId();

    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
        //.map(u->UserDto.fromEntity(u)

        }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No user was found with the provided ID"+ id));
    }

    @Override
    public void delete(Integer id) {
        //check before delete
        repository.deleteById(id);

    }


    @Override
    public Integer validateAccount(Integer id) {
        User user=repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("no user found for user accound validation"));
        user.setActive(true);
        //create bank account
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user=repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("no user found for user accound validation"));

        user.setActive(false);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer Edit(Integer id, UserDto dto) {

            // Fetch the existing user from the repository
            User existingUser = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

            // Validate the DTO
            validator.validate(dto);

            // Update the existing user entity with new values from the DTO
            existingUser.setFirstname(dto.getFirstname());
            existingUser.setLastname(dto.getLastname());
            existingUser.setEmail(dto.getEmail());
            existingUser.setAdresse(dto.getAdresse());
            existingUser.setActive(dto.isActive());
            // If password needs to be updated, encode it
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            // Log to ensure we are encoding the password
            System.out.println("Encoding password for user: " + id);
            // Encode the new password
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            // If no new password is provided, retain the existing password
            existingUser.setPassword(existingUser.getPassword());
        }
            // Update other fields as needed
            existingUser.setRole(findOrCreateRole(ROLE_USER));
            existingUser.setActive(dto.isActive());

            // Save the updated user entity
            return repository.save(existingUser).getId();


    }


    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );
        user.setActive(false);
        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = repository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }



    private Role findOrCreateRole(String roleName) {
        Role role = roleRepositroy.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepositroy.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }


    @Override
    public void changePassword(changePasswordRequest request, Principal connectedUser) {
        var user=(User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();


        // Check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // Check if the new password and confirm password match
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }
     //update
     user.setPassword(passwordEncoder.encode(request.getNewPassword()));
     //save
     repository.save(user);


    }





}
