package org.example.uberprojectauthservice.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private  String Secret;

     // this method will create a brand new token based on payload
    private String createToken(Map<String , Object> payload, String email){

          Date expiryDate = new Date(System.currentTimeMillis() + expiry * 1000L);
//        SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(payload)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .signWith(getSignKey())
                .compact();
    }

    private Claims extractAllPayloads(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolverFunction) {
        final Claims claim = extractAllPayloads(token);
        return resolverFunction.apply(claim);
    }

    private Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private String extractEmail(String token){
        return extractClaims(token, Claims::getSubject);
    }


    // this method will check is token is expired or not return true is expired else false;
    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes(StandardCharsets.UTF_8));
    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(Secret.getBytes(StandardCharsets.UTF_8));
    }

    private Boolean ValidateToken(String token, String email) {
        final String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
    }

    private String extractPhoneNnumber(String token){
        Claims claims = extractAllPayloads(token);
        String Number = (String) claims.get("phoneNumber");
        return Number;
    }

    private String extractPayload(String token , String payloadKey){
        Claims claims = extractAllPayloads(token);
        return (String) claims.get(payloadKey);
    }

    @Override
    public void run(@Nullable String... args) {
        Map<String , Object> mp = new HashMap<>();
        mp.put("email" , "abc@.com");
        mp.put("phoneNumber" , "99999999999");
        String email = "chirag";
        String result = createToken(mp , email);
        System.out.println("generated token is : " + result);
        System.out.println("extracted email is : " + extractEmail(result));
        System.out.println("token expired? : " + isTokenExpired(result));
        System.out.println("extracted phone number is : " + extractPhoneNnumber(result));
        System.out.println("extracted email is : " + extractPayload(result, "email"));
    }
}
