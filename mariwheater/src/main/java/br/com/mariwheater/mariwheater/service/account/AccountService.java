package br.com.mariwheater.mariwheater.service.account;

import br.com.mariwheater.mariwheater.DTO.DataAccount;
import br.com.mariwheater.mariwheater.model.Account;
import br.com.mariwheater.mariwheater.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void createAndSaveAccount (DataAccount dataAccount) {
        accountRepository.save(new Account(dataAccount));
    }

    public List<Account> getAllAccounts () {
        return accountRepository.findAll();
    }
}
