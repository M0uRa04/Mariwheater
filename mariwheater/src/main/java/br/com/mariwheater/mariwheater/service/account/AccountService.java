package br.com.mariwheater.mariwheater.service.account;

import br.com.mariwheater.mariwheater.dto.DataAccount;
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

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Account updateAccount(Account foundAccount, DataAccount dataAccount) {
        foundAccount.setCityName(dataAccount.cityName());
        foundAccount.setEmail(dataAccount.email());
        accountRepository.save(foundAccount);
        return foundAccount;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAccountByCityName (String cityName) {
        return accountRepository.getAccountByCityName(cityName);
    }
}
