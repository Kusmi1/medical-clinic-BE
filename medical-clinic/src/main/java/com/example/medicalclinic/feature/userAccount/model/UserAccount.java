package com.example.medicalclinic.feature.userAccount.model;

import com.example.medicalclinic.feature.user.model.User;
import com.example.medicalclinic.feature.visits.model.Visit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "userAccount")
public class UserAccount {

  @Id
  @Column(name = "user_id")
  private UUID id;

  @Column(name=" balance")
  private Double balance;


  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;


  @OneToMany(mappedBy = "userAccount")
  private List<Visit> visits;

}
