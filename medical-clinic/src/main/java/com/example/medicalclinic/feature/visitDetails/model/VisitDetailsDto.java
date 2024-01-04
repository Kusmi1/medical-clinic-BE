package com.example.medicalclinic.feature.visitDetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitDetailsDto {
  private String medicines;
  private String description;
}
