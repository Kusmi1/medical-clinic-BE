package com.example.medicalclinic.feature.specialization.service.impl;

import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.specialization.persistance.SpecializationRepository;
import com.example.medicalclinic.feature.specialization.service.SpecializationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceImpl  implements SpecializationService {
SpecializationRepository specializationRepository;

  public SpecializationServiceImpl(SpecializationRepository specializationRepository){
    this.specializationRepository=specializationRepository;
  }

  public List<Specialization> findAllSpecialization() {
return specializationRepository.findAll();
  }


}
