package com.example.medicalclinic.exception;

public class NoFutureBookedVisitsException extends RuntimeException {
  public NoFutureBookedVisitsException(String message) {
    super(message);
  }
}