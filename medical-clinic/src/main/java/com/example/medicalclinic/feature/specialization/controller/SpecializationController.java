package com.example.medicalclinic.feature.specialization.controller;

import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.specialization.service.impl.SpecializationServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/specialization")
public class SpecializationController {
  private SpecializationServiceImpl specializationService;

  public SpecializationController(SpecializationServiceImpl specializationService){
    this.specializationService=specializationService;
  }

  @GetMapping
  public List<Specialization> findAllSpecialization(){
   return specializationService.findAllSpecialization();
  }
}
