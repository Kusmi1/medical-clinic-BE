package com.example.medicalclinic.feature.visits.model;

import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinicDto;
import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VisitDTO {
  private UUID visitId;
  private String specialization;
  private UUID doctorId;
  private String doctorName;
  private String doctorSurname;
  private UUID userId;
  private String userName;
  private double price;
  private String userSurname;
  private String date;
  private String hour;
  private List<HourDTO> hours;
  private MedicalClinicDto medicalClinic;



  public void setUserInformation(UserAccount userAccount) {
    if (userAccount != null) {
      this.userId = userAccount.getId();
      this.userName = userAccount.getUser().getFirstName();
      this.userSurname = userAccount.getUser().getLastName();
    }
  }
}
