package com.finner.multitenant.util;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTExample {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Define um segredo para assinatura (pode ser gerado aleatoriamente)
        //Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    	// Generate a secret key using Java's cryptography API
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        keyGenerator.init(256); // You can adjust the key size as needed
        SecretKey secretKey = keyGenerator.generateKey();
        
        // Define uma data de expiração para o token (opcional)
        long expirationMillis = 3600000; // 1 hora
        Date expiration = new Date(System.currentTimeMillis() + expirationMillis);

        // Constrói o token JWT com a reivindicação "tools"
        String jwtToken = Jwts.builder()
                .claim("tools", true) // Define a reivindicação "tools" como verdadeira
                .setExpiration(expiration) // Define a data de expiração
                .signWith(SignatureAlgorithm.HS256, secretKey) // Assina o token com o segredo
                .compact();

        System.out.println("Token JWT gerado: " + jwtToken);
    }
}
