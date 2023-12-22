package com.example.medicalclinic.feature.visits.controller;

import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.service.impl.VisitServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/visit")
@CrossOrigin
public class VisitController {
  private VisitServiceImpl visitService;

  @Autowired
  public VisitController(VisitServiceImpl visitServiceImpl){
    this.visitService=visitServiceImpl;
  }

  @GetMapping()
  public List<Visit> findAllVisit() {
    return visitService.findAllVisits();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<VisitDTO>> getVisitsForUser(@PathVariable Long userId) {
    List<VisitDTO> visits = visitService.getAllVisitsForUser(userId);
    return ResponseEntity.ok(visits);
  }


  @GetMapping("/past-visit/user/{userId}")
  public ResponseEntity<?> getPastBookedVisits(@PathVariable Long userId) {
    try {
      List<VisitDTO> pastVisits = visitService.getPastBookedVisitsForUser(userId);

      if (pastVisits.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No past visits found for the user with ID: " + userId);
      }

      return ResponseEntity.ok(pastVisits);
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An error occurred while processing the request.");
    }
  }

  @GetMapping("/future-visit/user/{userId}")
  public ResponseEntity<?> getFutureBookedVisits(@PathVariable Long userId) {
    try {
      List<VisitDTO> futureVisits = visitService.getFutureBookedVisitsForUser(userId);

      if (futureVisits.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No future visits found for the user with ID: " + userId);
      }

      return ResponseEntity.ok(futureVisits);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An error occurred while processing the request: " + e.getMessage());
    }

  }

  @GetMapping("/{specializationName}")
  public ResponseEntity<List<VisitDTO>> getAvailableVisitsBySpecialization(@PathVariable String specializationName) {
    List<VisitDTO> availableVisits = visitService.getAvailableVisitsBySpecialization(specializationName);
    return ResponseEntity.ok(availableVisits);
  }
}


