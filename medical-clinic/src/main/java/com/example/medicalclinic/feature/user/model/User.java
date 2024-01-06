package com.example.medicalclinic.feature.user.model;

import com.example.medicalclinic.feature.role.model.Role;
import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table( name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;
  @NotBlank
  @Size( max = 20)
  private String firstName;

  @Size(max = 20)
  private String secondName;

  @NotBlank
  @Size( max = 20)
  private String lastName;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @Size(max = 11)
  private String pesel;


  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private UserAccount account;

  public User( String firstName, String secondName, String lastName, String username,
      String email,String pesel, String password) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.pesel = pesel;
    this.password = password;
  }
}