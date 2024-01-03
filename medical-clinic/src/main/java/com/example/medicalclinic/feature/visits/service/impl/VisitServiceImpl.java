package com.example.medicalclinic.feature.visits.service.impl;

import com.example.medicalclinic.exception.EmptyListException;
import com.example.medicalclinic.exception.VisitNotAvailableException;
import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.persistence.DoctorRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

  private VisitRepository visitRepository;
  private UserRepository userRepository;
  private DoctorRepository doctorRepository;

  public VisitServiceImpl(UserRepository userRepository, VisitRepository visitRepository,DoctorRepository doctorRepository) {
    this.visitRepository = visitRepository;
    this.userRepository = userRepository;
    this.doctorRepository = doctorRepository;
  }
  public List<Visit> findAllVisits() {
    return visitRepository.findAll();
  }

  public List<VisitDTO> getAllVisitsForUser(Long userId) {
    List<Visit> visits = visitRepository.findByUserAccountId(userId);
    return visits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }

  public List<VisitDTO> getAllVisitsForDoctor(Long userId) {
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

public List<VisitDTO> getAllFutureBookedVisits(Optional<Long> userId, Optional<Long> doctorId) {
  List<Visit> futureBookedVisits;


  if (!visitRepository.findAllFutureVisits(userId, doctorId,
      new Date()).isEmpty()) {
    futureBookedVisits = visitRepository.findAllFutureVisits(userId, doctorId,  new Date());
    System.out.println("futureBookedVisits all "+futureBookedVisits);
    return futureBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());

  }
    if (userId.isPresent() && visitRepository.findAllFutureVisits(userId, null, new Date())
        .isEmpty()) {
      futureBookedVisits = visitRepository.findAllFutureVisits(null, doctorId,
          new Date());
      System.out.println("futureBookedVisits userId"+futureBookedVisits+" userId "+userId);
      return futureBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());

    }
   if  (doctorId.isPresent() && visitRepository.findAllFutureVisits(null, doctorId, new Date())
      .isEmpty()) {
    futureBookedVisits=visitRepository.findAllFutureVisits(userId, null,
        new Date());
     System.out.println("futureBookedVisits doctorId"+futureBookedVisits+" userId "+doctorId);
  }else {
     System.out.println("futureBookedVisits dempoty list");
      return visitRepository.findAllFutureVisitsbyEmptyValue(new Date())
          .stream().map(this::convertToVisitDTO).collect(Collectors.toList());


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

  public void bookVisit (UUID visitId, Long userId) {
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
  public void deleteVisit(UUID visitId) throws NotFoundException {
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

  public VisitDTO getVisitById(UUID visitId) {
    Visit visit = visitRepository.findById(visitId)
        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
      return convertToVisitDTO(visit);

  }

  public void addVisit(Date visitDate, Long doctorId, String hours, int price) {
    try {
      Doctor doctor = doctorRepository.findById(doctorId)
          .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));


      if (isDoctorAvailable(doctor, visitDate, hours)) {
        Visit visit = new Visit();
        visit.setVisitDate(visitDate);
        visit.setAvailable(true);
        visit.setPrice(price);
        visit.setHours(hours);
        visit.setDoctor(doctor);

        visitRepository.save(visit);
      } else {
        throw new RuntimeException("Doctor is not available at the specified date and hour");
      }
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Error adding visit: Doctor not found");
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error adding visit");
    }
  }

  private boolean isDoctorAvailable(Doctor doctor, Date visitDate, String hours) {
    // Check if the doctor has a visit at the specified date and hour
    Optional<Visit> existingVisit = visitRepository.findByDoctorAndVisitDateAndHours(doctor, visitDate, hours);
    return existingVisit.isEmpty();
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

    UserAccount userAccount = visit.getUserAccount();
    if (userAccount != null) {
      dto.setUserId(userAccount.getId());
      dto.setUserName(userAccount.getUser().getFirstname());
      dto.setUserSurname(userAccount.getUser().getLastname());
    }
    return dto;
  }

}
