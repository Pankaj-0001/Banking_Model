package com.pankaj.Banking.Controller;

import com.pankaj.Banking.Service.AccountService;
import com.pankaj.Banking.Service.impl.AccountServiceimpl;
import com.pankaj.Banking.dto.AccountsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    AccountServiceimpl serviceimpl;

    public AccountsController(AccountServiceimpl serviceimpl) {
        this.serviceimpl = serviceimpl;
    }

    @PostMapping("/create_Account")
    public ResponseEntity<AccountsDto> addAccount(@RequestBody AccountsDto accountsDto){
        return new ResponseEntity<>(serviceimpl.addAccount(accountsDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsDto> getAccountbyId(@PathVariable long id){
        return new ResponseEntity<>(serviceimpl.getAccountbyId(id),HttpStatus.OK);
    }
    @PutMapping("/update_Account")
    public ResponseEntity<AccountsDto> updateAccount(@RequestBody AccountsDto accountsDto){
        AccountsDto accountsDto1=serviceimpl.updateAccount(accountsDto);
        if(accountsDto1!=null) {
            return new ResponseEntity<>(accountsDto1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/deposit-{id}")
    public ResponseEntity<AccountsDto> deposit(@PathVariable long id,@RequestBody Map<String,Double> deposit_request){

        AccountsDto accountsDto1=serviceimpl.deposit(id, deposit_request.get("deposit_amount"));
        if(accountsDto1!=new AccountsDto()) {
            return new ResponseEntity<>(accountsDto1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/withdraw-{id}")
    public ResponseEntity<AccountsDto> withdraw(@PathVariable long id,@RequestBody Map<String, Double> withdraw_request){
        AccountsDto accountsDto1=serviceimpl.withdraw(id,withdraw_request.get("withdraw_amount"));
        if(accountsDto1!=new AccountsDto()) {
            return new ResponseEntity<>(accountsDto1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountsDto>> getAllAccounts(){
        return new ResponseEntity<>(serviceimpl.getAllAcounts(),HttpStatus.OK);
    }


    @DeleteMapping("/deleteAccount-{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        serviceimpl.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Succesfully :)");
    }
}
