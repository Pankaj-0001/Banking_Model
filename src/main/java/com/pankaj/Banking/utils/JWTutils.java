package com.pankaj.Banking.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTutils {

    private String Secret_key = "$2a$12$69.5la4vfbLrVWSDgTprAuq3.gXHbFyaq2onoFsqglif0J0dF2kbW";


    public String generatetoken(String username) {
        Map<String,Object> claim = new HashMap<>();
        return Jwts.builder()
                .claims(claim)
                .subject(username)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*100))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Secret_key.getBytes());
    }
}
