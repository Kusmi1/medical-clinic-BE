package com.example.medicalclinic.feature.visits.service.impl;

import com.example.medicalclinic.exception.EmptyListException;
import com.example.medicalclinic.exception.VisitNotAvailableException;
import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.visits.model.HourDTO;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.persistence.VisitRepository;
import com.example.medicalclinic.feature.visits.service.VisitService;
import jakarta.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

  public List<VisitDTO> getAllVisitsForDocotr(Long userId) {
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

  public List<VisitDTO> getAvailableVisitsBySpecialization(String specializationName, Optional<Date> choosenDate) {
    if (choosenDate.isEmpty()) {
      choosenDate = Optional.of(new Date());
    }

    List<Visit> availableVisits = visitRepository.findAvailableVisitsBySpecializationOrderedByDate(specializationName, choosenDate);
    Map<String, VisitDTO> uniqueVisitsMap = new HashMap<>();

    for (Visit currentVisit : availableVisits) {
      String key = currentVisit.getDoctor().getId() + "_" + currentVisit.getVisitDate();

      if (!uniqueVisitsMap.containsKey(key)) {
        List<HourDTO> hours = new ArrayList<>();
        HourDTO hourDto = new HourDTO();
        hourDto.setVisitId(currentVisit.getId());
        hourDto.setHour(currentVisit.getHours());
        hours.add(hourDto);

        VisitDTO dto = new VisitDTO();
        dto.setSpecialization(specializationName);
        dto.setDoctorId(currentVisit.getDoctor().getId());
        dto.setDoctorName(currentVisit.getDoctor().getName());
        dto.setDoctorSurname(currentVisit.getDoctor().getSurname());
        dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(currentVisit.getVisitDate()));
        dto.setHour(currentVisit.getHours());
        dto.setHours(hours);

        uniqueVisitsMap.put(key, dto);
      } else {

        VisitDTO existingVisitDTO = uniqueVisitsMap.get(key);
        HourDTO hourDto = new HourDTO();
        hourDto.setVisitId(currentVisit.getId());
        hourDto.setHour(currentVisit.getHours());
        existingVisitDTO.getHours().add(hourDto);
      }
    }

    return new ArrayList<>(uniqueVisitsMap.values());
  }

  public void bookVisit (Long visitId, Long userId) {
    try {
      Visit visit = visitRepository.findById(visitId)
          .orElseThrow(() -> {
            System.out.println("Visit not found for ID: " + visitId);
            return new IllegalArgumentException("Visit not found");
          });

      User user = userRepository.findById(userId)
          .orElseThrow(() -> {
            System.out.println("User not found for ID: " + userId);
            return new IllegalArgumentException("User not found");
          });

      UserAccount userAccount = user.getAccount();

      if (visit.getAvailable() && visit.getUserAccount() == null) {
        visit.setUserAccount(userAccount);
        visit.setAvailable(false);
        visitRepository.save(visit);
      } else {
        throw new VisitNotAvailableException("Visit is not available or already assigned.");
      }
    } catch (EntityNotFoundException e) {
      throw new VisitNotAvailableException("Visit not found");
    }
  }
  public void deleteVisit(Long visitId) throws NotFoundException {
    try {
    Visit visit = visitRepository.findById(visitId)
        .orElseThrow(() -> new NotFoundException());

    if (!visit.getAvailable() && visit.getUserAccount() != null) {
    visit.setAvailable(true);
    visit.setUserAccount(null);
    visitRepository.save(visit);

  } else {
    throw new VisitNotAvailableException("Visit is not assigned.");
  }
    }
    catch (EntityNotFoundException e) {
      throw new VisitNotAvailableException("Visit not found");
    }
  }

  public VisitDTO getVisitById(Long visitId) {
    Visit visit = visitRepository.findById(visitId)
        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
      return convertToVisitDTO(visit);

  }

  private VisitDTO convertToVisitDTO(Visit visit) {
    Set<Specialization> specializations = visit.getDoctor().getSpecializations();
    String specializationNames = specializations.stream()
        .map(Specialization::getName)
        .collect(Collectors.joining(", "));

    List<HourDTO> hours = new ArrayList<>();
    HourDTO hourDto = new HourDTO();
    hourDto.setVisitId(visit.getId());
    hourDto.setHour(visit.getHours());
    hours.add(hourDto);

    VisitDTO dto = new VisitDTO();
    dto.setVisitId(visit.getId());
    dto.setSpecialization(specializationNames);
    dto.setDoctorId(visit.getDoctor().getId());
    dto.setDoctorName(visit.getDoctor().getName());
    dto.setDoctorSurname(visit.getDoctor().getSurname());
    dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(visit.getVisitDate()));
    dto.setHour(visit.getHours());
    dto.setHours(hours);
    return dto;
  }

}
