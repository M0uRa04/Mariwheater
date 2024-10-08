package br.com.mariwheater.mariwheater.repository;

import br.com.mariwheater.mariwheater.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account, Long> {
    List<Account> getAccountByCityName(String cityName);

}
