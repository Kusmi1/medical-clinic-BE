package com.example.medicalclinic.feature.user.controller;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.service.impl.UserServiceImpl;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
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

}
