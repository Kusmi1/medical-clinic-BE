package com.example.medicalclinic.feature.doctor.controller;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.service.impl.DoctorServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

  public final DoctorServiceImpl doctorService;
  @Autowired
  public DoctorController(DoctorServiceImpl doctorService) {
    this.doctorService = doctorService;
  }

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
}
