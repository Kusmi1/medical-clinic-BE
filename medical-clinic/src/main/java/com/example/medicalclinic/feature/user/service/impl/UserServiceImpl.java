package com.example.medicalclinic.feature.user.service.impl;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  private UserDto convertToDto(User user){
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getFirstname());
    userDto.setSurname(user.getLastname());
    return userDto;
  }
}
