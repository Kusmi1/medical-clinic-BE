package com.example.medicalclinic.feature.specialization.controller;

import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.specialization.service.SpecializationService;
import com.example.medicalclinic.feature.specialization.service.impl.SpecializationServiceImpl;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class SpecializationController {
  @Autowired
  private SpecializationServiceImpl specializationService;

  @GetMapping("/specialization")
  public List<Specialization> getAllSpecializations() {
    return specializationService.findAllSpecialization();
  }
}
