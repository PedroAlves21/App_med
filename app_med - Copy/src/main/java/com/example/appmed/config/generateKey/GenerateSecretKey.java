package com.example.appmed.config.generateKey;

import io.jsonwebtoken.SignatureAlgorithm;  // Adicione esta linha
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);  // Certifique-se de que SignatureAlgorithm.HS512 está correto
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Chave secreta em Base64: " + base64Key);
    }
}
