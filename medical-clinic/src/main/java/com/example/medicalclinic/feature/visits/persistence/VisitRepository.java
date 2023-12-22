package com.example.medicalclinic.feature.visits.persistence;

import com.example.medicalclinic.feature.visits.model.Visit;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
  List<Visit> findByUserAccountId(Long userId);

  List<Visit> findByUserAccountIdAndAvailableAndVisitDateBefore(Long userId, boolean available, Date currentDate);
  List<Visit> findByUserAccountIdAndAvailableAndVisitDateAfter(Long userId, boolean available, Date currentDate);

  @Query("SELECT v FROM Visit v " +
      "WHERE :specializationName IN (SELECT s.name FROM v.doctor.specializations s) " +
      "AND v.available = true " +
      "AND v.visitDate >= current_date " +
      "ORDER BY v.visitDate")
  List<Visit> findAvailableVisitsBySpecializationOrderedByDate(
      @Param("specializationName") String specializationName
  );

}
