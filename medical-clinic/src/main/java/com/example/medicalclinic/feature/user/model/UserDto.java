package com.example.medicalclinic.feature.user.model;

import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
  private UUID id;
  private String name;
  private String secondName;
  private String lastname;
  private String userName;
  private String email;
  private String pesel;
  private Double balance;
}
