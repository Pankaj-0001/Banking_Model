package com.pankaj.Banking.Service;

import com.pankaj.Banking.model.Users;
import com.pankaj.Banking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class Myuserdetailservice implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if(user != null){
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(String.valueOf(Collections.singleton(new SimpleGrantedAuthority("USER"))))
                    .build();
        }
        else {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
    }
}
