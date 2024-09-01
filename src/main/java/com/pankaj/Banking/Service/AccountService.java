package com.pankaj.Banking.Service;

import com.pankaj.Banking.dto.AccountsDto;
import com.pankaj.Banking.model.Account;

import java.util.List;

public interface AccountService {
    AccountsDto addAccount(AccountsDto accountsDto);

    AccountsDto getAccountbyId(long id);

    AccountsDto updateAccount(AccountsDto accountsDto);

    AccountsDto deposit(long id,double amount);

    AccountsDto withdraw(long id,double amount);

    List<AccountsDto> getAllAcounts();

    void deleteAccount(long id);

}
