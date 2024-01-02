package com.example.medicalclinic.feature.visits.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HourDTO {
  private Long visitId;
  private String hour;
}
