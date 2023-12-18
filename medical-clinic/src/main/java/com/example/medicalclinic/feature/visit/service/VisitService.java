package com.example.medicalclinic.feature.visit.service;

import com.example.medicalclinic.feature.visit.model.Visit;
import java.util.List;

public interface VisitService {
  List<Visit> findAllVisits();
}
