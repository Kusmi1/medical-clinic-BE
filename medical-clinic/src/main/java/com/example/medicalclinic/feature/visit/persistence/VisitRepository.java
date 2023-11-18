package com.example.medicalclinic.feature.visit.persistence;

import com.example.medicalclinic.feature.visit.model.Visit;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {
}
