package com.example.medicalclinic.feature.doctor.service.impl;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.persistence.DoctorRepository;
import com.example.medicalclinic.feature.doctor.service.DoctorService;
import java.util.List;
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
}
