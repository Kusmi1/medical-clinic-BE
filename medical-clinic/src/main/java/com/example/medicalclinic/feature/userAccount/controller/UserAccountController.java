package com.example.medicalclinic.feature.userAccount.controller;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.userAccount.service.impl.UserAccountServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/useraccount")
@CrossOrigin
public class UserAccountController {


  private final UserAccountServiceImpl userAccountService;

  public UserAccountController(UserAccountServiceImpl userAccountService) {
    this.userAccountService = userAccountService;
  }


  @GetMapping("/getaccount")
  List<UserAccount> getAllUserAccounts() {
    return userAccountService.getAllUserAccounts();
  }

  @PutMapping("/setbalance/{userId}")
  public ResponseEntity<String> updateBalance(@PathVariable UUID userId, @RequestParam Double balance) {
    try {
      userAccountService.updateBalance(userId, balance);
      return ResponseEntity.ok("Balance updated successfully");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating balance");
    }
  }
}
