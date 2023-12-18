package com.example.medicalclinic.feature.visit.service.impl;

import com.example.medicalclinic.feature.user.persistence.UserRepository;
import com.example.medicalclinic.feature.visit.model.Visit;
import com.example.medicalclinic.feature.visit.persistence.VisitRepository;
import com.example.medicalclinic.feature.visit.service.VisitService;
import java.util.List;
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

}
