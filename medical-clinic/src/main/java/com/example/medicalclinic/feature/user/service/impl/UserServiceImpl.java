package com.example.medicalclinic.feature.user.service.impl;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.persistence.DoctorRepository;
import com.example.medicalclinic.feature.role.model.ERole;
import com.example.medicalclinic.feature.role.model.Role;
import com.example.medicalclinic.feature.role.persistence.RoleRepository;
import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.user.service.UserService;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.persistence.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private VisitRepository visitRepository;

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

  public UserDto getUserDtoById(UUID userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

    return convertToDto(user);
  }

  @Transactional
  public void updateUserData(UUID userId, UserDto userDto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    user.setFirstName(userDto.getName());
    user.setSecondName(userDto.getSecondName());
    user.setLastName(userDto.getLastname());
    user.setEmail(userDto.getEmail());
    userRepository.save(user);
  }


  public void changeUserRoleAndSpecialization(UUID userId, ERole newRole,
      Set<Specialization> specializations) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Role roleEntity = roleRepository.findByName(newRole)
        .orElseThrow(() -> new RuntimeException("Role not found"));

    Set<Role> roles = new HashSet<>();
    roles.add(roleEntity);
    user.setRoles(roles);

    if (newRole == ERole.ROLE_DOCTOR) {
      Doctor doctor = doctorRepository.findById(userId).orElse(new Doctor());
      doctor.setId(userId);
      doctor.setName(user.getFirstName());
      doctor.setSurname(user.getLastName());
      doctor.setSpecializations(specializations != null ? specializations : new HashSet<>());

      doctorRepository.save(doctor);
    }
    userRepository.save(user);
  }


  public List<UserDto> getUsersWithDoctorAndNurseRoles() {
    List<ERole> roleNames = Arrays.asList(ERole.ROLE_USER, ERole.ROLE_NURSE);

    List<User> users = userRepository.findAllUsersByRoles_NameIn(roleNames);

    return users.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
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

  public void deleteUserByID(UUID userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not Found"));

    if (user.getRoles().stream().anyMatch(
        role -> role.getName() == ERole.ROLE_USER || role.getName() == ERole.ROLE_NURSE)) {

      List<Visit> visits = visitRepository.findAllByUserAccountUserId(userId);

      for (Visit visit : visits) {
        visit.setUserAccount(null);
        visit.setAvailable(true);
        visitRepository.save(visit);
      }
      userRepository.delete(user);
    }
  }
}
