package com.example.medicalclinic.feature.visitDetails.controller;

import com.example.medicalclinic.feature.visitDetails.model.VisitDetails;
import com.example.medicalclinic.feature.visitDetails.model.VisitDetailsDto;
import com.example.medicalclinic.feature.visitDetails.service.VisitDetailsService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visit-details")
@CrossOrigin
public class VisitDetailsController {

  private final VisitDetailsService visitDetailsService;

  @Autowired
  public VisitDetailsController(VisitDetailsService visitDetailsService) {
    this.visitDetailsService = visitDetailsService;
  }

  @GetMapping("/{visitId}")
  public ResponseEntity<VisitDetailsDto> getVisitDetailsByVisitId(@PathVariable UUID visitId) {
    VisitDetails visitDetails = visitDetailsService.getVisitDetailsByVisitId(visitId);
    VisitDetailsDto visitDetailsDto = visitDetailsService.convertToDto(visitDetails);
    return ResponseEntity.ok(visitDetailsDto);
  }

  @PostMapping("/add/{visitId}")
  public ResponseEntity<VisitDetailsDto> addVisitDetails(@PathVariable UUID visitId, @RequestBody VisitDetailsDto visitDetailsDto) {
    VisitDetailsDto createdVisitDetails = visitDetailsService.addVisitDetailsToVisit(visitId, visitDetailsDto);
    return new ResponseEntity<>(createdVisitDetails, HttpStatus.CREATED);
  }
}