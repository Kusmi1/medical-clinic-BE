package com.example.medicalclinic.feature.userAccount.persistence;

import com.example.medicalclinic.feature.userAccount.model.UserAccount;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
}

