package br.com.mariwheater.mariwheater.repository;

import br.com.mariwheater.mariwheater.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
}
