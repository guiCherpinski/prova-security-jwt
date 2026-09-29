package br.prova.jwt.provajwt.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class TokenService {

    @Value("{jwt.secret}")
    private String secret;

    public String gerarToken(UserDetails userDetails){
        try{
            return Jwts.builder()
                    .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"))))
                    .signWith(getKey())
                    .setSubject(userDetails.getUsername())
                    .setExpiration(Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))))
                    .claim(userDetails.getUsername(),extractClaims(userDetails))
                    .compact();

        }catch (Exception e){
            return null;
        }
    }

    public SecretKey getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims extractClaims(UserDetails userDetails){
        return Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"))))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))));

    }


}
