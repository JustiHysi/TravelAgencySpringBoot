package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dto.UserDto;
import com.example.TravelAgency.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private PasswordEncoder passEncoder;
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAll(){
        return  ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/findById/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") long userId) {
        return new ResponseEntity<>(userService.findById(userId),HttpStatus.OK);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable("userId") long userId){
        return ResponseEntity.ok(userService.update(userDto,userId));
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") Long userId) {
        userService.delete(userId);

        return ResponseEntity.ok("User with id; " +userId+" was deleted");
    }
    @GetMapping("/encode/{pass}")
    public ResponseEntity<String>basicEncode(@PathVariable("pass") String pass){
        return ResponseEntity.ok(passEncoder.encode(pass));

    }
}