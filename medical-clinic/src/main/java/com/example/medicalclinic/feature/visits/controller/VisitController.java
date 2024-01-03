package com.example.medicalclinic.feature.visits.controller;

import com.example.medicalclinic.exception.VisitNotAvailableException;
import com.example.medicalclinic.feature.visits.model.HourDTO;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.service.impl.VisitServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visit")
@CrossOrigin
public class VisitController {

  private VisitServiceImpl visitService;

  @Autowired
  public VisitController(VisitServiceImpl visitServiceImpl) {
    this.visitService = visitServiceImpl;
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


  @GetMapping("/all-future-visits")
  public ResponseEntity<?> getAllFutureBookedVisits(
      @RequestParam(name = "userId", required = false) Long userId,
      @RequestParam(name = "doctorId", required = false) Long doctorId) {
    try {
      List<VisitDTO> futureVisits = visitService.getAllFutureBookedVisits(
          Optional.ofNullable(userId), Optional.ofNullable(doctorId));

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
  public ResponseEntity<List<VisitDTO>> getAvailableVisitsBySpecialization(
      @PathVariable String specializationName,
      @RequestParam(name = "visitDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date visitDate) {
    List<VisitDTO> availableVisits = visitService.getAvailableVisitsBySpecialization(
        specializationName,
        Optional.ofNullable(visitDate));
    return ResponseEntity.ok(availableVisits);
  }

  @GetMapping("/byId/{visitId}")
  public ResponseEntity<VisitDTO> getVisitById(@PathVariable UUID visitId) {
    try {
      VisitDTO visitDTO = visitService.getVisitById(visitId);
      return new ResponseEntity<>(visitDTO, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/visitId/{visitId}/user/{userId}")
  public ResponseEntity<String> bookVisit(@PathVariable UUID visitId, @PathVariable Long userId) {
    try {
      visitService.bookVisit(visitId, userId);
      return ResponseEntity.ok("User added to visit successfully.");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visit or User not found.");

    } catch (VisitNotAvailableException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/visitId/{visitId}")
  public ResponseEntity<String> deleteVisitFromUser(@PathVariable("visitId") UUID visitId)
      throws NotFoundException {
    try {
      visitService.deleteVisit(visitId);
      return ResponseEntity.ok("Visit updated successfully");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visit or User not found.");

    } catch (VisitNotAvailableException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

//  @PostMapping
  @PostMapping("/add-visit")
  public ResponseEntity<String> addVisit(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitDate,
      @RequestParam Long doctorId,
      @RequestParam String hours,
      @RequestParam int price
  ) {
    try {
      visitService.addVisit(
          visitDate,
          doctorId,
          hours,
          price);
      return ResponseEntity.ok("Visit added successfully.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}


