package com.example.medicalclinic.feature.user.service.impl;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

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

  public UserDto getUserDtoById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

    return convertToDto(user);
  }

  @Transactional
  public void updateUserData(Long userId, UserDto userDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    User user = (User) authentication.getPrincipal();

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    user.setFirstName(userDto.getName());
    user.setSecondName(userDto.getSecondName());
    user.setLastName(userDto.getLastname());
    user.setEmail(userDto.getEmail());
    userRepository.save(user);
  }


  private UserDto convertToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getFirstName());
    userDto.setSecondName(user.getSecondName());
    userDto.setLastname(user.getLastName());
    userDto.setUserName(user.getUsername());
    userDto.setEmail(user.getEmail());
    userDto.setPesel(user.getPesel());

    return userDto;
  }
}
