package com.example.medicalclinic.feature.role.persistence;

import com.example.medicalclinic.feature.role.model.ERole;
import com.example.medicalclinic.feature.role.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
