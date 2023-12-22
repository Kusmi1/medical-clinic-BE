package com.example.medicalclinic.feature.user.controller;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.service.impl.UserServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserServiceImpl userService;

  @Autowired
  public  UserController(UserServiceImpl userService){
    this.userService=userService;
  }
  @GetMapping
  public List<User> findAllUsers() {
    return userService.findAllUsers();
  }

}
