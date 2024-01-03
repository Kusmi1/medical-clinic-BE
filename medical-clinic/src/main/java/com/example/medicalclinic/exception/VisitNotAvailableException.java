package com.example.medicalclinic.exception;
public class VisitNotAvailableException extends RuntimeException {

  public VisitNotAvailableException(String message) {
    super(message);
  }
}