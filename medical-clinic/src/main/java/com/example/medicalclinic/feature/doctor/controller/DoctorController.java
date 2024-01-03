package com.example.medicalclinic.feature.doctor.controller;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.model.DoctorDTO;
import com.example.medicalclinic.feature.doctor.service.impl.DoctorServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/doctor")
@CrossOrigin
public class DoctorController {
  @Autowired
  public final DoctorServiceImpl doctorService;


  @GetMapping
  public List<Doctor> getAllDoctors(){
    return doctorService.getAllDoctors();
  }
  @GetMapping("/all")
  public ResponseEntity<List<Doctor>> getAllDoctors2() {
    List<Doctor> doctors = doctorService.getAllDoctors();
    if (!doctors.isEmpty()) {
      return new ResponseEntity<>(doctors, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

@GetMapping("/specialization/{specializationName}")
public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialization(@PathVariable String specializationName) {
  List<DoctorDTO> doctorInfoDTOs = doctorService.getDoctorsInfoBySpecialization(specializationName);
  return new ResponseEntity<>(doctorInfoDTOs, HttpStatus.OK);
}

}
