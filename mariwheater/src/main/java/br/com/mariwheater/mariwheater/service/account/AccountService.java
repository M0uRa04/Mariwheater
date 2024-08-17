package br.com.mariwheater.mariwheater.service.account;

import br.com.mariwheater.mariwheater.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
}
