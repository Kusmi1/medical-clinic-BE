package com.example.medicalclinic.feature.visitDetails.model;

import com.example.medicalclinic.feature.visits.model.Visit;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_visitDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDetails {


  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "medicine", length=1000)
  private String medicines;

  @Column(name = "description", length=1000)
  private String description;

  @Column(name = "pin", length=4)
  private String pin;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private Visit visit;

}
