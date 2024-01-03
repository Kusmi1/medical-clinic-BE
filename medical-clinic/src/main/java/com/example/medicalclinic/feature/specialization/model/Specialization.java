package com.example.medicalclinic.feature.specialization.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_specialization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specialization {

  @Id
  @Column(name="specialization_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column (name ="name")
  private String name;
}
