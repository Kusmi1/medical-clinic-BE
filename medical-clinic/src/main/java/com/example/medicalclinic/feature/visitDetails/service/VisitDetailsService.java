package com.example.medicalclinic.feature.visitDetails.service;

import com.example.medicalclinic.feature.visitDetails.model.VisitDetails;
import com.example.medicalclinic.feature.visitDetails.model.VisitDetailsDto;
import com.example.medicalclinic.feature.visitDetails.persistence.VisitDetailsRepository;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.persistence.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitDetailsService {

  private final VisitDetailsRepository visitDetailsRepository;
  private final VisitRepository visitRepository;

  @Autowired
  public VisitDetailsService(VisitDetailsRepository visitDetailsRepository, VisitRepository visitRepository) {
    this.visitDetailsRepository = visitDetailsRepository;
    this.visitRepository = visitRepository;
  }

  public VisitDetails getVisitDetailsByVisitId(UUID visitId) {
    return visitDetailsRepository.findByVisitId(visitId)
        .orElseThrow(
            () -> new EntityNotFoundException("VisitDetails not found for visit ID: " + visitId));
  }

  public VisitDetailsDto convertToDto(VisitDetails visitDetails) {
    VisitDetailsDto dto = new VisitDetailsDto();
    dto.setMedicines(visitDetails.getMedicines());
    dto.setDescription(visitDetails.getDescription());
    return dto;
  }

  public VisitDetailsDto addVisitDetailsToVisit(UUID visitId, VisitDetailsDto visitDetailsDto) {
    Visit visit = visitRepository.findById(visitId)
        .orElseThrow(() -> new EntityNotFoundException("Visit not found with ID: " + visitId));

    VisitDetails visitDetails = visitDetailsRepository.findById(visitId)
        .orElseGet(() -> {
          VisitDetails newVisitDetails = new VisitDetails();
          newVisitDetails.setVisit(visit);
          return newVisitDetails;
        });

    visitDetails.setMedicines(visitDetailsDto.getMedicines());
    visitDetails.setDescription(visitDetailsDto.getDescription());

    visit.setVisitDetails(visitDetails);

    VisitDetails savedVisitDetails = visitDetailsRepository.save(visitDetails);
    return convertToDto(savedVisitDetails);
  }
}