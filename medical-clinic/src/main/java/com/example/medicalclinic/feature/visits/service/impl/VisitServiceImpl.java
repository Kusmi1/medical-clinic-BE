package com.example.medicalclinic.feature.visits.service.impl;

import com.example.medicalclinic.exception.CustomNotFoundException;
import com.example.medicalclinic.exception.EmptyListException;
import com.example.medicalclinic.exception.NotEnoughMoneyException;
import com.example.medicalclinic.exception.VisitNotAvailableException;
import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.doctor.persistence.DoctorRepository;
import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinicDto;
import com.example.medicalclinic.feature.medicalClinic.persistence.MedicalClinicRepository;
import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.userAccount.persistence.UserAccountRepository;
import com.example.medicalclinic.feature.visitDetails.model.VisitDetails;
import com.example.medicalclinic.feature.visits.model.HourDTO;
import com.example.medicalclinic.feature.visits.model.Visit;
import com.example.medicalclinic.feature.visits.model.VisitDTO;
import com.example.medicalclinic.feature.visits.persistence.VisitRepository;
import com.example.medicalclinic.feature.visits.service.VisitService;
import jakarta.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
  private UserAccountRepository userAccountRepository;
  private DoctorRepository doctorRepository;
  private MedicalClinicRepository medicalClinicRepository;

  public VisitServiceImpl(UserRepository userRepository, VisitRepository visitRepository,
      DoctorRepository doctorRepository, MedicalClinicRepository medicalClinicRepository, UserAccountRepository userAccountRepository) {
    this.visitRepository = visitRepository;
    this.userRepository = userRepository;
    this.userAccountRepository = userAccountRepository;
    this.doctorRepository = doctorRepository;
    this.medicalClinicRepository = medicalClinicRepository;
  }

  public List<Visit> findAllVisits() {
    return visitRepository.findAll();
  }

  public List<VisitDTO> getAllVisitsForUser(UUID userId) {
    List<Visit> visits = visitRepository.findByUserAccountId(userId);
    return visits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }

  public List<VisitDTO> getPastBookedVisitsForUser(UUID userId) {

    List<Visit> pastBookedVisits = visitRepository.findByUserAccountIdAndAvailableAndVisitDateBefore(
        userId, false, new Date());
    if (pastBookedVisits.isEmpty()) {
      return Collections.emptyList();
    }
    pastBookedVisits.sort((v1, v2) -> v2.getVisitDate().compareTo(v1.getVisitDate()));
    return pastBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }

  public List<VisitDTO> getFutureBookedVisitsForUser(UUID userId) {
    List<Visit> futureBookedVisits = visitRepository.findByUserAccountIdAndAvailableAndVisitDateAfter(
        userId, false, new Date());
    if (futureBookedVisits.isEmpty()) {
      return Collections.emptyList();}

    futureBookedVisits.sort((v1, v2) -> v1.getVisitDate().compareTo(v2.getVisitDate()));
    return futureBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
  }



    public List<VisitDTO> getAllFutureBookedVisits(Optional<UUID> userId, Optional<UUID> doctorId) {

      List<Visit> futureBookedVisits=new ArrayList<>();

      if (doctorId.isPresent() && userId.isPresent()) {
        futureBookedVisits = visitRepository.findAllFutureVisits(
            userId.orElse(null), doctorId.orElse(null), new Date());
      } else if (futureBookedVisits.isEmpty()&& !userId.isPresent() && doctorId.isPresent()) {
        futureBookedVisits = visitRepository.findByDoctorIdAndAvailableAndVisitDateAfter(
            doctorId.orElse(null), false, new Date());

      } else if (futureBookedVisits.isEmpty()&&userId.isPresent() && !doctorId.isPresent()) {
        futureBookedVisits = visitRepository.findByUserAccountIdAndAvailableAndVisitDateAfter(
            userId.orElse(null), false, new Date());
      } else {
        futureBookedVisits = visitRepository.findAllFutureVisitsbyEmptyValue(new Date());
      }

      if (futureBookedVisits.isEmpty()) {
        return Collections.emptyList();
      }

      return futureBookedVisits.stream().map(this::convertToVisitDTO).collect(Collectors.toList());
    }



  public List<VisitDTO> getAvailableVisitsBySpecialization(String specializationName,
      Optional<Date> choosenDate) {
    List<Visit> availableVisits;

    if (choosenDate.isEmpty()) {

      choosenDate = Optional.ofNullable(null);
      availableVisits = visitRepository.findAvailableVisitsBySpecializationOrderedByCurrentDate(
          specializationName);
      availableVisits.sort((v1, v2) -> v1.getVisitDate().compareTo(v2.getVisitDate()));

    } else {
      availableVisits = visitRepository.findAvailableVisitsBySpecializationOrderedByDate(
          specializationName, choosenDate);

        if (availableVisits.isEmpty()) {
          throw new EmptyListException("No visits are available for this date.");
        }
    }

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
        dto.setVisitId(currentVisit.getId());
        dto.setSpecialization(specializationName);
        dto.setDoctorId(currentVisit.getDoctor().getId());
        dto.setDoctorName(currentVisit.getDoctor().getName());
        dto.setDoctorSurname(currentVisit.getDoctor().getSurname());
        dto.setPrice(currentVisit.getPrice());
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

    List<VisitDTO> sortedVisits = new ArrayList<>(uniqueVisitsMap.values());
    sortedVisits.sort((dto1, dto2) -> {
      int dateCompare = dto1.getDate().compareTo(dto2.getDate());
      if (dateCompare != 0) {
        return dateCompare;
      } else {
        return dto1.getHours().get(0).getHour().compareTo(dto2.getHours().get(0).getHour());
      }
    });
    return sortedVisits;
  }

  public void bookVisit(UUID visitId, UUID userId) {
    try {
      Visit visit = visitRepository.findById(visitId)
          .orElseThrow(() -> {
            return new IllegalArgumentException("Visit not found");
          });

      User user = userRepository.findById(userId)
          .orElseThrow(() -> {
            return new IllegalArgumentException("User not found");
          });

      UserAccount userAccount = user.getAccount();

      if (visit.getAvailable() && visit.getUserAccount() == null) {
        visit.setUserAccount(userAccount);
        visit.setAvailable(false);
        if(userAccount.getBalance()>=visit.getPrice()) {
          userAccount.setBalance(userAccount.getBalance() - visit.getPrice());
          visitRepository.save(visit);
        } else throw new NotEnoughMoneyException("User doesn't have enought money");

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
      UserAccount userAccount = visit.getUserAccount();
      if (!visit.getAvailable() && visit.getUserAccount() != null) {
        visit.setAvailable(true);
        visit.setUserAccount(null);
        userAccount.setBalance(userAccount.getBalance() + visit.getPrice());
        userAccountRepository.save(userAccount);
        visitRepository.save(visit);

      } else {
        throw new VisitNotAvailableException("Visit is not assigned.");
      }
    } catch (EntityNotFoundException e) {
      throw new VisitNotAvailableException("Visit not found");
    }
  }

  public VisitDTO getVisitById(UUID visitId) {
    Visit visit = visitRepository.findById(visitId)
        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
    return convertToVisitDTO(visit);
  }


public void addVisit(Date visitDate, UUID doctorId, String startHour, String endHour, int stepMinutes, int price, Long clinicId) {
  try {
    Doctor doctor = doctorRepository.findById(doctorId)
        .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

    MedicalClinic medicalClinic = medicalClinicRepository.findById(clinicId)
        .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

    LocalTime startTime = LocalTime.parse(startHour);
    LocalTime endTime = LocalTime.parse(endHour);

    while (startTime.isBefore(endTime)) {
      if (isDoctorAvailable(doctor, visitDate, startTime.toString())) {
        VisitDetails visitDetails = new VisitDetails();
        visitDetails.setDescription("");
        visitDetails.setMedicines("");

        Visit visit = new Visit();
        visit.setVisitDate(visitDate);
        visit.setAvailable(true);
        visit.setPrice(price);
        visit.setHours(startTime.toString());
        visit.setDoctor(doctor);
        visit.setMedicalClinic(medicalClinic);
        visit.setVisitDetails(visitDetails);
        visitDetails.setVisit(visit);
        visitRepository.save(visit);
      } else {
        throw new RuntimeException("Doctor is not available at " + startTime.toString());
      }
      startTime = startTime.plusMinutes(stepMinutes);
    }
  } catch (EntityNotFoundException e) {
    e.printStackTrace();
    throw new RuntimeException("Error adding visit: " + e.getMessage());
  } catch (Exception e) {
    e.printStackTrace();
    throw new RuntimeException("Error adding visit: " + e.getMessage());
  }
}


  private boolean isDoctorAvailable(Doctor doctor, Date visitDate, String hours) {
    Optional<Visit> existingVisit = visitRepository.findByDoctorAndVisitDateAndHours(doctor,
        visitDate, hours);
    return existingVisit.isEmpty();
  }

  private VisitDTO convertToVisitDTO(Visit visit) {
    Set<Specialization> specializations = visit.getDoctor().getSpecializations();
    String specializationNames = specializations.stream()
        .map(Specialization::getName)
        .collect(Collectors.joining(", "));

    MedicalClinic clinic = medicalClinicRepository.findById(visit.getMedicalClinic().getId())
        .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

    List<HourDTO> hours = new ArrayList<>();
    HourDTO hourDto = new HourDTO();
    hourDto.setVisitId(visit.getId());
    hourDto.setHour(visit.getHours());
    hours.add(hourDto);

    MedicalClinicDto clinicDto = new MedicalClinicDto();
    clinicDto.setId(clinic.getId());
    clinicDto.setName(clinic.getName());
    clinicDto.setStreet(clinic.getStreet());
    clinicDto.setCity(clinic.getCity());
    clinicDto.setPostalCode(clinic.getPostalCode());
    clinicDto.setFlatNo(clinic.getFlatNo());
    clinicDto.setHouseNo(clinic.getHouseNo());

    VisitDTO dto = new VisitDTO();
    dto.setVisitId(visit.getId());
    dto.setSpecialization(specializationNames);
    dto.setDoctorId(visit.getDoctor().getId());
    dto.setDoctorName(visit.getDoctor().getName());
    dto.setDoctorSurname(visit.getDoctor().getSurname());
    dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(visit.getVisitDate()));
    dto.setHour(visit.getHours());
    dto.setHours(hours);
    dto.setMedicalClinic(clinicDto);
    dto.setPrice(visit.getPrice());

    UserAccount userAccount = visit.getUserAccount();
    if (userAccount != null) {
      dto.setUserId(userAccount.getId());
      dto.setUserName(userAccount.getUser().getFirstName());
      dto.setUserSurname(userAccount.getUser().getLastName());
    }
    return dto;
  }

}
