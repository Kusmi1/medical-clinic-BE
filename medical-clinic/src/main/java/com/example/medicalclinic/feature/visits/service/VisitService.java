package com.example.medicalclinic.feature.visits.service;

import com.example.medicalclinic.feature.visits.model.Visit;
import java.util.List;

public interface VisitService {
  List<Visit> findAllVisits();
}
