package com.example.medicalclinic.feature.doctor.service.impl;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.model.DoctorDTO;
import com.example.medicalclinic.feature.doctor.persistence.DoctorRepository;
import com.example.medicalclinic.feature.doctor.service.DoctorService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
  private final DoctorRepository doctorRepository;
  public  DoctorServiceImpl (DoctorRepository doctorRepository){
    this.doctorRepository = doctorRepository;
  }

  public List<Doctor> getAllDoctors() {
    return doctorRepository.findAll();
  }

  public List<DoctorDTO> getDoctorsInfoBySpecialization(String specializationName) {
    List<Doctor> doctors = doctorRepository.findBySpecializationsName(specializationName);
    return doctors.stream()
        .map(DoctorDTO::from)
        .collect(Collectors.toList());
  }
}
