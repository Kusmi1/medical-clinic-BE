package com.example.medicalclinic.feature.medicalClinic.persistence;

import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalClinicRepository extends JpaRepository<MedicalClinic, Long> {

}