package com.example.medicalclinic.feature.user.controller;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.service.impl.UserServiceImpl;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
  private final UserServiceImpl userService;

  @Autowired
  public  UserController(UserServiceImpl userService){
    this.userService=userService;
  }
  @GetMapping("/all")
  public List<UserDto> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
    UserDto userDto = userService.getUserDtoById(userId);
    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/add/{userId}")
  public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
    userService.updateUserData(userId,userDto);
    return ResponseEntity.ok().build();
  }
}
