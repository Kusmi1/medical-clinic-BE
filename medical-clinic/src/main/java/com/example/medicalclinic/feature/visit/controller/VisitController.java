package com.example.medicalclinic.feature.visit.controller;

import com.example.medicalclinic.feature.visit.model.Visit;
import com.example.medicalclinic.feature.visit.service.impl.VisitServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visit")
@CrossOrigin
public class VisitController {
  private VisitServiceImpl visitServiceImpl;

  @Autowired
  public VisitController(VisitServiceImpl visitServiceImpl){
    this.visitServiceImpl=visitServiceImpl;
  }

  @GetMapping()
  public List<Visit> findAllVisit() {
    return visitServiceImpl.findAllVisits();
  }

}
