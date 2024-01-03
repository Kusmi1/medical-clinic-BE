package com.example.medicalclinic.feature.visits.model;

import com.example.medicalclinic.feature.medicalClinic.model.MedicalClinic;
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
  private Long doctorId;
  private String doctorName;
  private String doctorSurname;
  private Long userId;
  private String userName;
  private String userSurname;
  private String date;
  private String hour;
  private List<HourDTO> hours;

  private Long medicalClinicId;
  private String medicalClinicName;
  private String medicalClinicStreet;
  private String medicalClinicHouseNo;
  private String medicalClinicFlatNo;
  private String medicalClinicPostalCode;

  public void setUserInformation(UserAccount userAccount) {
    if (userAccount != null) {
      this.userId = userAccount.getId();
      this.userName = userAccount.getUser().getFirstname();
      this.userSurname = userAccount.getUser().getLastname();
    }
  }

  public void setMedicalClinicInformation(MedicalClinic medicalClinic) {
    if (medicalClinic != null) {
      this.medicalClinicId = medicalClinic.getId();
      this.medicalClinicName = medicalClinic.getName();
      this.medicalClinicStreet = medicalClinic.getStreet();
      this.medicalClinicHouseNo = medicalClinic.getHouseNo();
      this.medicalClinicFlatNo = medicalClinic.getFlatNo();
      this.medicalClinicPostalCode = medicalClinic.getPostalCode();
    }
  }
}