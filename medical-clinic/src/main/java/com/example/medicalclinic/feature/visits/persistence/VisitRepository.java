package com.example.medicalclinic.feature.visits.persistence;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.visits.model.Visit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {

  List<Visit> findByUserAccountId(UUID userId);

  List<Visit> findByUserAccountIdAndAvailableAndVisitDateBefore(UUID userId, boolean available,
      Date currentDate);

  List<Visit> findByUserAccountIdAndAvailableAndVisitDateAfter(UUID userId, boolean available,
      Date currentDate);

  List<Visit> findByDoctorIdAndAvailableAndVisitDateAfter(UUID doctorId, boolean available,
      Date currentDate);

  @Query("SELECT v FROM Visit v WHERE " +
      " v.userAccount.user.id = :userId AND " +
      " v.doctor.id = :doctorId AND " +
      "(COALESCE(:userId, :doctorId) IS NOT NULL OR v.available = false) AND " +
      "v.visitDate > :currentDate " +
      "ORDER BY v.visitDate")
  List<Visit> findAllFutureVisits(
      @Param("userId") UUID userId,
      @Param("doctorId") UUID doctorId,
      @Param("currentDate") Date currentDate
  );

  @Query("SELECT v FROM Visit v WHERE " +
      " v.available = false AND " +
      "v.visitDate > :currentDate " +
      "ORDER BY v.visitDate")
  List<Visit> findAllFutureVisitsbyEmptyValue(
      @Param("currentDate") Date currentDate
  );

  @Query("SELECT v FROM Visit v " +
      "WHERE :specializationName IN (SELECT s.name FROM v.doctor.specializations s) " +
      "AND v.available = true " +
      "AND v.visitDate = date(:visitDate) " +
      "ORDER BY  v.visitDate")
  List<Visit> findAvailableVisitsBySpecializationOrderedByDate(
      @Param("specializationName") String specializationName,
      @Param("visitDate") Optional<Date> visitDate);

  @Query("SELECT v FROM Visit v " +
      "WHERE :specializationName IN (SELECT s.name FROM v.doctor.specializations s) " +
      "AND v.available = true " +
      "AND  v.visitDate  > Date(CURRENT_DATE ) " +
      "ORDER BY v.visitDate")
  List<Visit> findAvailableVisitsBySpecializationOrderedByCurrentDate(
      @Param("specializationName") String specializationName);

  List<Visit> findByDateOfAddingVisit(Date dateOfAddingVisit);

  @Query("SELECT v FROM Visit v " +
      "WHERE :specializationName IN (SELECT s.name FROM v.doctor.specializations s) " +
      "AND v.dateOfAddingVisit = :dateOfAddingVisit " +
      "ORDER BY  v.visitDate")
  List<Visit> findByDateOfAddingVisitAndSpecializationName(
      @Param("dateOfAddingVisit") Date dateOfAddingVisit,
      @Param("specializationName") Optional<String> specializationName);


  Optional<Visit> findByDoctorAndVisitDateAndHours(Doctor doctor, Date visitDate, String hours);

  List<Visit> findAllByUserAccountUserId(UUID userId);
}
