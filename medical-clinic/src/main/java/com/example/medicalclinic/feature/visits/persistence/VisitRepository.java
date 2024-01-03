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
  List<Visit> findByUserAccountId(Long userId);

  List<Visit> findByUserAccountIdAndAvailableAndVisitDateBefore(Long userId, boolean available, Date currentDate);
  List<Visit> findByUserAccountIdAndAvailableAndVisitDateAfter(Long userId, boolean available, Date currentDate);
  @Query("SELECT v FROM Visit v WHERE " +
      "(:userId IS NULL OR v.userAccount.user.id = :userId) AND " +
      "(:doctorId IS NULL OR v.doctor.id = :doctorId) AND " +
      "(COALESCE(:userId, :doctorId) IS NOT NULL OR v.available = false) AND " +
      "v.visitDate > :currentDate")
  List<Visit> findAllFutureVisits(
      @Param("userId") Optional<Long> userId,
      @Param("doctorId") Optional<Long> doctorId,
      @Param("currentDate") Date currentDate
  );

  @Query("SELECT v FROM Visit v WHERE " +

      " v.available = false AND " +
      "v.visitDate > :currentDate")
  List<Visit> findAllFutureVisitsbyEmptyValue(

      @Param("currentDate") Date currentDate
  );

  @Query("SELECT v FROM Visit v " +
      "WHERE :specializationName IN (SELECT s.name FROM v.doctor.specializations s) " +
      "AND v.available = true " +
      "AND v.visitDate >= DATE(:visitDate)" +
      "ORDER BY v.visitDate")
  List<Visit> findAvailableVisitsBySpecializationOrderedByDate(
      @Param("specializationName")   String specializationName,
      @Param("visitDate") Optional<Date> visitDate);

  Optional<Visit> findByDoctorAndVisitDateAndHours(Doctor doctor, Date visitDate, String hours);
}
