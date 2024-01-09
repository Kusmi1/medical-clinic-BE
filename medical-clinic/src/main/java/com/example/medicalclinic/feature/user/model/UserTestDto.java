package com.example.medicalclinic.feature.user.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserTestDto {

  private UUID id;
  private String name;
  private String lastname;
}