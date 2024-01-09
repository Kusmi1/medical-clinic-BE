package com.example.medicalclinic.feature.user.persistence;

import com.example.medicalclinic.feature.role.model.ERole;
import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.model.UserTestDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);

  List<User> findAllUsersByRoles_NameIn(List<ERole> roleNames);
}
