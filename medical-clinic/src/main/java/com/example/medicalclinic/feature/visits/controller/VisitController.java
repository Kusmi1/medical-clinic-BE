package com.example.medicalclinic.feature.visits.controller;

import com.example.medicalclinic.exception.EmptyListException;
import com.example.medicalclinic.exception.NotEnoughMoneyException;
import com.example.medicalclinic.exception.VisitNotAvailableException;
import com.example.medicalclinic.feature.visits.model.HourDTO;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.service.impl.VisitServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
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
  public ResponseEntity<List<VisitDTO>> getVisitsForUser(@PathVariable UUID userId) {
    List<VisitDTO> visits = visitService.getAllVisitsForUser(userId);
    return ResponseEntity.ok(visits);
  }


  @GetMapping("/past-visit/user/{userId}")
  public ResponseEntity<List<VisitDTO>>  getPastBookedVisits(@PathVariable UUID userId) {
    try {
      List<VisitDTO> pastVisits = visitService.getPastBookedVisitsForUser(userId);


      return ResponseEntity.ok(pastVisits);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.emptyList());
    }
  }

  @GetMapping("/future-visit/user/{userId}")
  public ResponseEntity<List<VisitDTO>> getFutureBookedVisits(@PathVariable UUID userId) {
    try {
      List<VisitDTO> futureVisits = visitService.getFutureBookedVisitsForUser(userId);

      return ResponseEntity.ok(futureVisits);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.emptyList());
    }
  }


  @GetMapping("/all-future-visits")
  public ResponseEntity<?> getAllFutureBookedVisits(
      @RequestParam(name = "userId", required = false) UUID userId,
      @RequestParam(name = "doctorId", required = false) UUID doctorId) {
    try {
      List<VisitDTO> futureVisits = visitService.getAllFutureBookedVisits(
          Optional.ofNullable(userId), Optional.ofNullable(doctorId));

      if (futureVisits.isEmpty() && doctorId==null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No future visits found for the user with ID: " + userId);
      }

      if (futureVisits.isEmpty() && userId==null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No future visits found for the doctor with ID: " + doctorId);
      }

      return ResponseEntity.ok(futureVisits);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An error occurred while processing the request: " + e.getMessage());
    }
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
  public ResponseEntity<String> bookVisit(@PathVariable UUID visitId, @PathVariable UUID userId) {
    try {
      visitService.bookVisit(visitId, userId);
      return ResponseEntity.ok("User added to visit successfully.");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visit or User not found.");

    } catch (VisitNotAvailableException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    catch(NotEnoughMoneyException e){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Not Money ");

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
  @PostMapping("/add-visit")
  public ResponseEntity<String> addVisit(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitDate,
      @RequestParam UUID doctorId,
      @RequestParam String startHour,
      @RequestParam String endHour,
      @RequestParam int stepHour,
      @RequestParam int price,
      @RequestParam Long clinicId
  ) {
    try {
      visitService.addVisit(
          visitDate,
          doctorId,
          startHour,
          endHour,
          stepHour,
          price,
          clinicId);
      return ResponseEntity.ok("Visit(s) added successfully.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    }
}

  @GetMapping("/{specializationName}")
  public ResponseEntity<List<VisitDTO>> getAvailableVisitsBySpecialization(
      @PathVariable String specializationName,
      @RequestParam(name = "visitDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date visitDate) {
    try{
      List<VisitDTO> availableVisits = visitService.getAvailableVisitsBySpecialization(
          specializationName,
          Optional.ofNullable(visitDate));
      return ResponseEntity.ok(availableVisits);
    }
    catch(EmptyListException e){
      List<VisitDTO> availableVisits = visitService.getAvailableVisitsBySpecialization(
          specializationName,
          Optional.empty());
      return new ResponseEntity<>(availableVisits, HttpStatus.NOT_FOUND);
    }
  }
}


