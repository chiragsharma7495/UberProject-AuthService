package org.example.uberprojectauthservice.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private  String Secret;

     // this method will create a brand new token based on payload
    private String createToken(Map<String , Object> payload, String username){

        Date expiryDate = new Date(System.currentTimeMillis() + expiry * 1000);
        SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes());

        return Jwts.builder()
                .claims(payload)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String , Object> mp = new HashMap<>();
        mp.put("email" , "abc@.com");
        mp.put("phoneNumber" , "99999999999");
        String result = createToken(mp ,"chirag");
        System.out.println("generated token is : " + result);
    }
}
