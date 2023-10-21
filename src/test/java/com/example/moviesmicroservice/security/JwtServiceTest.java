package com.example.moviesmicroservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    public void checkValidToken_DoesntThrowException() {
        //Arrange
        String token = "Bearer " + generateToken();
        //Act and Assert
        assertDoesNotThrow(() -> jwtService.checkToken(token));

    }

    @Test
    public void checkInvalidToken_ThrowsException() {
        //Arrange
        String token = generateToken();
        //Act and Assert
        assertThrows(MalformedJwtException.class,() -> jwtService.checkToken(token));

    }


    public String generateToken() {
        return Jwts
                .builder()
                .setSubject("test@email.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 30))
                .signWith(jwtService.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
