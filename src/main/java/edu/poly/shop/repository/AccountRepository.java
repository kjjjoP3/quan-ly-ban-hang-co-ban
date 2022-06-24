package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
