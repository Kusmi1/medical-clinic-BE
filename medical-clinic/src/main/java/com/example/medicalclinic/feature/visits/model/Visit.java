package com.example.medicalclinic.feature.visits.model;

import com.example.medicalclinic.feature.doctor.model.Doctor;
import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "_visit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

  @Id
  @GeneratedValue
//  (strategy = GenerationType.IDENTITY)@Column(name = "visit_id")
  private UUID id;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "visitDate")
  private Date visitDate;


  @Column(name = "available")
  private Boolean available;

  @Column(name = "price")
  private double price;


  @Column(name = "hour")
  private String hours;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userAccount_id")
  private UserAccount userAccount;
  @Override
  public String toString() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(visitDate);
  }
}
