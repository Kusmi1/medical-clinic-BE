package com.example.medicalclinic.feature.visits.service.impl;

import com.example.medicalclinic.exception.EmptyListException;
import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.persistence.VisitRepository;
import com.example.medicalclinic.feature.visits.service.VisitService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

  private VisitRepository visitRepository;
  private UserRepository userRepository;

  public VisitServiceImpl(UserRepository userRepository, VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
    this.userRepository = userRepository;
  }
  public List<Visit> findAllVisits() {
    return visitRepository.findAll();
  }

  public List<VisitDTO> getAllVisitsForUser(Long userId) {
    List<Visit> visits = visitRepository.findByUserAccountId(userId);
    return visits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }


  public List<VisitDTO> getPastBookedVisitsForUser(Long userId) {

    List<Visit> pastBookedVisits = visitRepository.findByUserAccountIdAndAvailableAndVisitDateBefore(userId, false, new Date());
    if (pastBookedVisits.isEmpty()) {
      throw new EmptyListException("No past booked visits found for the user.");
    }
    return pastBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }

  public List<VisitDTO> getFutureBookedVisitsForUser(Long userId) {
    List<Visit> futureBookedVisits = visitRepository.findByUserAccountIdAndAvailableAndVisitDateAfter(userId, false, new Date());

    if (futureBookedVisits.isEmpty()) {
      throw new EmptyListException("No future booked visits found for the user.");
    }
    return futureBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }


  public List<VisitDTO> getAvailableVisitsBySpecialization(String specializationName) {
    List<Visit> availableVisits = visitRepository.findAvailableVisitsBySpecializationOrderedByDate(specializationName);
    return availableVisits.stream()
        .map(this::convertToVisitDTO)
        .collect(Collectors.toList());
  }

  private VisitDTO convertToVisitDTO(Visit visit) {
    Set<Specialization> specializations = visit.getDoctor().getSpecializations();
    String specializationNames = specializations.stream()
        .map(Specialization::getName)
        .collect(Collectors.joining(", "));

    VisitDTO dto = new VisitDTO();
    dto.setSpecialization(specializationNames);
    dto.setDoctorName(visit.getDoctor().getName());
    dto.setDoctorSurname(visit.getDoctor().getSurname());
    dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(visit.getVisitDate()));
    dto.setHour(visit.getHours());
    return dto;
  }


}
