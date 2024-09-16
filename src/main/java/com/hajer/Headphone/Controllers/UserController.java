package com.hajer.Headphone.Controllers;


import com.hajer.Headphone.Dto.UserDto;
import com.hajer.Headphone.Dto.changePasswordRequest;
import com.hajer.Headphone.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService service;
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto){

        return ResponseEntity.ok(service.save(userDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        Integer userId = service.Edit(id, userDto);
        return ResponseEntity.ok(userId);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") Integer id){
        return ResponseEntity.ok((service.findById(id)));

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/validate/{userId}")
    public  ResponseEntity<Integer> validateAccount(@PathVariable("userId") Integer id){
        return ResponseEntity.ok(service.validateAccount(id));

    }
    @PatchMapping("/invalidate/{userId}")
    public  ResponseEntity<Integer> invalidateAccount(@PathVariable("userId") Integer id){
        return ResponseEntity.ok(service.invalidateAccount(id));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Integer id){
        service.delete(id);
    return ResponseEntity.accepted().build();
    }

   @PatchMapping("/changepassword")
    public ResponseEntity<?>changePassword(
            @RequestBody changePasswordRequest request,
            Principal connectedUser
   )
   {
       service.changePassword(request,connectedUser);

       return  ResponseEntity.ok().build();

   }
}
