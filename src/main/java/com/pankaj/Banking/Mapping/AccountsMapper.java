package com.pankaj.Banking.Mapping;

import com.pankaj.Banking.dto.AccountsDto;
import com.pankaj.Banking.model.Account;

public class AccountsMapper {
    public static Account maptoAccount(AccountsDto accountsDto){
        Account account =new Account(
                accountsDto.getId(),
                accountsDto.getAccountOwnerName(),
                accountsDto.getBalance()
        );
        return account;
    }
    public static AccountsDto maptoAccountdto(Account account){
        AccountsDto accountdto = new AccountsDto(
                account.getId(),
                account.getAccountOwnerName(),
                account.getBalance()
        );
        return accountdto;
    }
}
