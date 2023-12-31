package com.example.medicalclinic.feature.medicalClinic.model;

import com.example.medicalclinic.feature.visits.model.Visit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "medicalClinic")
public class MedicalClinic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="medical_clinic_id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "house_no", nullable = false)
  private String houseNo;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "flat_no")
  private String flatNo;

  @OneToMany(mappedBy = "medicalClinic")
  private List<Visit> visits;
}