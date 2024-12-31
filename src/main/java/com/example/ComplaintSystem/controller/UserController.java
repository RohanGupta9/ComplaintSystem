package com.example.ComplaintSystem.controller;

import com.example.ComplaintSystem.dto.UsersDto;
import com.example.ComplaintSystem.exception.AccessDenied;
import com.example.ComplaintSystem.exception.AlreadyExistsException;
import com.example.ComplaintSystem.exception.ResourceNotFound;
import com.example.ComplaintSystem.model.Users;
import com.example.ComplaintSystem.response.ApiResponse;
import com.example.ComplaintSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/userInfo/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId){
        try{
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }
        catch (ResourceNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users userDto){

        try{
            userService.RegisterUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(AlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsersDto userDto){
        try{
            return new ResponseEntity<>(userService.verify(userDto),HttpStatus.OK);
        }
        catch (AccessDenied e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AccessDenied("Access Denied"));
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UsersDto userDto, @RequestParam int userId){
        try{
            return new ResponseEntity<>(userService.updateUser(userDto,userId),HttpStatus.OK);
        }
        catch(ResourceNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse());
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam int userId){
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse());
        }
    }
}
