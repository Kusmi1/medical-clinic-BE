package com.example.medicalclinic.feature.doctor.persistence;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID >{
  List<Doctor> findBySpecializationsName(String specializationName);
}
