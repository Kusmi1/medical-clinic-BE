package com.example.medicalclinic.auth.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
///api/test/all
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Main Page";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('NURSE') or hasRole('DOCTOR')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('NURSE')")
  public String moderatorAccess() {
    return "Nurse Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('DOCTOR')")
  public String adminAccess() {
    return "Doctor Board.";
  }
}
