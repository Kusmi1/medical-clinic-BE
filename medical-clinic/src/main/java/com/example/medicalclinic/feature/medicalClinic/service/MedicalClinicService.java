package com.example.medicalclinic.feature.medicalClinic.service;

import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinicDto;
import com.example.medicalclinic.feature.medicalClinic.persistence.MedicalClinicRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalClinicService {
  private MedicalClinicRepository medicalClinicRepository;

  @Autowired
  public MedicalClinicService(MedicalClinicRepository medicalClinicRepository) {
    this.medicalClinicRepository = medicalClinicRepository;
  }

  public List<MedicalClinicDto> getAllMedicalClinics() {
    List<MedicalClinic> clinics = medicalClinicRepository.findAll();
    return clinics.stream().
        map(this::converttoDto)
        .collect(Collectors.toList());
  }

  private MedicalClinicDto converttoDto(MedicalClinic clinic){
    MedicalClinicDto clinicDto = new MedicalClinicDto();
    clinicDto.setId(clinic.getId());
    clinicDto.setName(clinic.getName());
    clinicDto.setStreet(clinic.getStreet());
    clinicDto.setCity(clinic.getCity());
    clinicDto.setPostalCode(clinic.getPostalCode());
    clinicDto.setFlatNo(clinic.getFlatNo());
    clinicDto.setHouseNo(clinic.getHouseNo());
    return clinicDto;
  }
}