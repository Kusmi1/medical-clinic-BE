package com.example.medicalclinic.feature.medicalClinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalClinicDto {
  private Long id;
  private String name;
  private String city;
  private String postalCode;
  private String street;
  private String houseNo;
  private String flatNo;
}
