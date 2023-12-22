package com.example.medicalclinic.feature.doctor.model;

import com.example.medicalclinic.feature.specialization.model.Specialization;
import com.example.medicalclinic.feature.visits.model.Visit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
  @Id
  @GeneratedValue
  @Column(name = "doctor_id")
  private Long id;

  @Column( name = "name")
  private String name;

  @Column( name = "Surname")
  private String surname;

  @ManyToMany
  @JoinTable(
      name = "doctor_specialization",
      joinColumns = @JoinColumn(name = "doctor_id"),
      inverseJoinColumns = @JoinColumn(name = "specialization_id")
  )
  private Set<Specialization> specializations = new HashSet<>();

  @OneToMany(mappedBy = "doctor")
  private List<Visit> visits;


  public Set<Specialization> getSpecialization() {
    return specializations;
  }

}
