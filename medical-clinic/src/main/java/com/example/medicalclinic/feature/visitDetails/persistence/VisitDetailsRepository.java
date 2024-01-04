package com.example.medicalclinic.feature.visitDetails.persistence;

import com.example.medicalclinic.feature.visitDetails.model.VisitDetails;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitDetailsRepository extends JpaRepository<VisitDetails, UUID> {
  Optional<VisitDetails> findByVisitId(UUID visitId);
}
