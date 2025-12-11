package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services;


import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.JwkParserBuilder;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;


    //We create a default secret key, then we will get the key merged with some hmascShakeyfor function
    private SecretKey getSecreteKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //In this method we are goin to take the user from our user entity and take in and create a token
    public String generateToken(User user){

        //Builder use to build token
        /*
        We add in -- user, parameterm issueAt, expiration, signWith
         */
        return
            Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(getSecreteKey())
                .compact();
    }

    //Now we have to decode the token to get the user id
    public Long getUserIdFromTokan(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecreteKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }


}
