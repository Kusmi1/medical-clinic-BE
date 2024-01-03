package com.example.medicalclinic.feature.medicalClinic.controller;

import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinicDto;
import com.example.medicalclinic.feature.medicalClinic.service.MedicalClinicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicalClinics")
@CrossOrigin
public class MedicalClinicController {
  @Autowired
  private final MedicalClinicService medicalClinicService;


  @GetMapping("/all-clinics")
  public ResponseEntity<List<MedicalClinicDto>> getAllMedicalClinics() {
    List<MedicalClinicDto> clinics = medicalClinicService.getAllMedicalClinics();
    if (!clinics.isEmpty()) {
      return new ResponseEntity<>(clinics, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
