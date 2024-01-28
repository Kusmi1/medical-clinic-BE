package com.example.medicalclinic.feature.user.controller;

import com.example.medicalclinic.feature.role.model.ERole;
import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.specialization.persistance.SpecializationRepository;
import com.example.medicalclinic.feature.user.model.UserDto;
import com.example.medicalclinic.feature.user.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

  private final UserServiceImpl userService;

  @Autowired
  private SpecializationRepository specializationRepository;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public List<UserDto> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
    UserDto userDto = userService.getUserDtoById(userId);
    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/add/{userId}")
  public ResponseEntity<Object> updateUser(@PathVariable UUID userId,
      @RequestBody UserDto userDto) {
    userService.updateUserData(userId, userDto);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/change-role/{userId}")
  public ResponseEntity<?> changeUserRoleAndHandleDoctor(
      @PathVariable UUID userId,
      @RequestParam ERole newRole,
      @RequestParam(required = false) Set<Long> specializationIds) {

    try {
      Set<Specialization> specializations = null;
      if (newRole == ERole.ROLE_DOCTOR) {
        specializations = new HashSet<>();
        for (Long id : specializationIds) {
          Optional<Specialization> specializationOptional = specializationRepository.findById(id);
          if (specializationOptional.isPresent()) {
            specializations.add(specializationOptional.get());
          } else {
            return ResponseEntity.badRequest().body("Specialization not found with id: " + id);
          }
        }
      }

      userService.changeUserRoleAndSpecialization(userId, newRole, specializations);
      return ResponseEntity.ok().body("User role updated successfully.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error updating user role: " + e.getMessage());
    }
  }

  @GetMapping("/allByRole")
  public List<UserDto> getUsersWithDoctorAndNurseRoles() {
    return  userService.getUsersWithDoctorAndNurseRoles();
  }

  @DeleteMapping("/delete/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('NURSE') or hasRole('DOCTOR')")
  public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
    try {
      userService.deleteUserByID(userId);
      return ResponseEntity.ok().body("User deleted successfully");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
    }
  }
}
