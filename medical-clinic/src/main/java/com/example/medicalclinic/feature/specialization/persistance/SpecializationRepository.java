package com.example.medicalclinic.feature.specialization.persistance;


import com.example.medicalclinic.feature.specialization.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}
