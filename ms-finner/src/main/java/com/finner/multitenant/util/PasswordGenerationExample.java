package com.finner.multitenant.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerationExample {
    public static void main(String[] args) {
        // Crie um codificador BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Gere uma senha criptografada
        String rawPassword = "tools"; // Substitua por sua senha real
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Imprima a senha criptografada
        System.out.println("Senha criptografada: " + encodedPassword);
    }
}
