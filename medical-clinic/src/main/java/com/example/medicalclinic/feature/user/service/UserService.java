package com.example.medicalclinic.feature.user.service;


import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import java.util.List;

public interface UserService {
  public List<UserDto> findAllUsers();
}
