package com.pankaj.Banking.Service.impl;

import com.pankaj.Banking.Mapping.AccountsMapper;
import com.pankaj.Banking.Service.AccountService;
import com.pankaj.Banking.dto.AccountsDto;
import com.pankaj.Banking.model.Account;
import com.pankaj.Banking.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceimpl implements AccountService {


    private AccountRepo repo;

    public AccountServiceimpl(AccountRepo repo) {
        this.repo = repo;
    }

    @Override
    public AccountsDto addAccount(AccountsDto accountsDto) {
        Account account = AccountsMapper.maptoAccount(accountsDto);
        Account savedAcc = repo.save(account);

        return AccountsMapper.maptoAccountdto(savedAcc);
    }

    @Override
    public AccountsDto getAccountbyId(long id) {
        Account account=repo.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        return AccountsMapper.maptoAccountdto(account);
    }

    @Override
    public AccountsDto updateAccount(AccountsDto accountsDto) {
        if(repo.existsById(accountsDto.getId())) {
            Account account = AccountsMapper.maptoAccount(accountsDto);
            Account savedAcc = repo.save(account);

            return AccountsMapper.maptoAccountdto(savedAcc);
        }
        return null;
    }

    @Override
    public AccountsDto deposit(long id, double amount) {
        if(repo.existsById(id)) {
            Account account = repo.findById(id).get();
            account.setBalance(account.getBalance()+amount);
            Account savedAcc =repo.save(account);

            return AccountsMapper.maptoAccountdto(savedAcc);
        }
        return new AccountsDto();
    }

    @Override
    public AccountsDto withdraw(long id, double amount) {
        if(repo.existsById(id)) {
            Account account = repo.findById(id).get();
            if(account.getBalance()>amount) {
                account.setBalance(account.getBalance() - amount);
                Account savedAcc = repo.save(account);

                return AccountsMapper.maptoAccountdto(savedAcc);
            }
            else throw new RuntimeException("Insufficient amount in the account");

        }
        return new AccountsDto();
    }

    @Override
    public List<AccountsDto> getAllAcounts() {
        List<Account> all_accounts = repo.findAll();
        List<AccountsDto> all_accountsDto = new ArrayList<>();
        for (Account acc:all_accounts) {
            all_accountsDto.add(AccountsMapper.maptoAccountdto(acc));
        }
        return all_accountsDto;
    }

    @Override
    public void deleteAccount(long id) {
        Account account=repo.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("Account doesn't exist"));
        repo.deleteById(id);
    }

}
