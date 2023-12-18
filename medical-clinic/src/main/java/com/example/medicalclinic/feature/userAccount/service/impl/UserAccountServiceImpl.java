package com.example.medicalclinic.feature.userAccount.service.impl;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.userAccount.persistence.UserAccountRepository;
import com.example.medicalclinic.feature.userAccount.service.UserAccountService;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountRepository userAccountRepository;

  public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

 public  List<UserAccount> getAllUserAccounts() {
    return userAccountRepository.findAll();
  }
}