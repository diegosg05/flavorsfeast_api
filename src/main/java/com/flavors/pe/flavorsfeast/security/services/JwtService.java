package com.flavors.pe.flavorsfeast.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.time.expiration}")
    private String TIME_EXPIRATION;

    // GENERATE AN ACCESS TOKEN
    // CREATE AN INSTANCE OF HashMap AND SEND THE User IMPLEMENTED UserDetails
    public String getToken(UserDetails userDetails){
        return getToken(new HashMap<>(), userDetails);
    }

    // BUILD TOKEN
    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(TIME_EXPIRATION)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // DECODE AND CODE THE KEY AND OBTAIN IT
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public String getUsernameFromToken(String token) {
        // Claims::getSubject retorna una funcion que retorna el sujeto o subject del token
        return getClaim(token, Claims::getSubject);
    }

    // THE TOKEN IS VALID???
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getClaims(token);
        return claimResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
