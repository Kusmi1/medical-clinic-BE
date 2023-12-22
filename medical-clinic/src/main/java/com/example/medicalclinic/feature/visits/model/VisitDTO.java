package com.example.medicalclinic.feature.visits.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VisitDTO {
  private String specialization;
  private String doctorName;
  private String doctorSurname;
  private String date;
  private String hour;

}
