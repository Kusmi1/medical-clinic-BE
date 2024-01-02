package com.example.medicalclinic.feature.visits.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VisitDTO {
  private Long visitId;
  private String specialization;
  private Long doctorId;
  private String doctorName;
  private String doctorSurname;
  private String date;
  private String hour;
  private List<HourDTO> hours;

}
