package com.example.medicalclinic.feature.userAccount.service.impl;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;
import com.example.medicalclinic.feature.userAccount.persistence.UserAccountRepository;
import com.example.medicalclinic.feature.userAccount.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
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

  public void updateBalance(UUID userId, Double balance) {
    UserAccount userAccount = userAccountRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("UserAccount not found with ID: " + userId));

    userAccount.setBalance(balance);
    userAccountRepository.save(userAccount);
  }
}