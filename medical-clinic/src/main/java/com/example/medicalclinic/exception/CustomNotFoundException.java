package com.example.medicalclinic.exception;

public class CustomNotFoundException extends RuntimeException {

  public CustomNotFoundException(String message) {
    super(message);
  }
}