package com.example.medicalclinic.feature.userAccount.controller;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.userAccount.service.impl.UserAccountServiceImpl;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useraccount")
@CrossOrigin
public class UserAccountController {


  private final UserAccountServiceImpl userAccountService;

  public UserAccountController(UserAccountServiceImpl userAccountService) {
    this.userAccountService = userAccountService;
  }


  @GetMapping
  List<UserAccount> getAllUserAccounts() {
    return userAccountService.getAllUserAccounts();
  }
}
