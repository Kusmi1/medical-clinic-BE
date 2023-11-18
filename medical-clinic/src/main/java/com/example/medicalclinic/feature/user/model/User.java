package com.example.medicalclinic.feature.user.model;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private UUID id;

  @NotBlank(message = "Username cannot be empty")
  @Column(unique = true, name = "username", nullable = false)

  private String username;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @NotBlank(message = "Email can not be empty")
  @Email(message = "Please insert a valid message")
  @Column(unique = true, name = "email", nullable = false)
  private String email;

  @NotBlank(message = "Password can not be empty")
  @Column(name = "password", nullable = false)
  private String password;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private UserAccount account;
}
