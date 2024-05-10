package com.riwi.beautySalon.infrastructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.riwi.beautySalon.domain.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    // crear una variable para guardar mi llave privada(FIRMA)
    private static final String SECRET_KEY = "bWlFbXBhbmFkaXRhQ29uQWppUGljYW50ZTEyMzQ1Njc4OTEw";

    // metodo que se va a encargar de retornar la llave de forma encriptada
    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        // retornar nuestra llave encriptada
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // metodo para construir nuestro token
    public String getToken(Map<String, Object> claims, User user) {
        return Jwts.builder()
                // agregamos el payload del JWT
                .claims(claims)
                // agregamos de quien es el JWT
                .subject(user.getUsername())
                // agregar cuando se creo el token
                .issuedAt(new Date(System.currentTimeMillis()))
                // agregamos la fecha de expiracion (en este caso, es un dia)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                // esto es para que el servidor firme el token
                .signWith(this.getKey())
                .compact();
    }

    //metodo para retornar el token con los claims configurados
    public String getToken(User user){

        Map<String,Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());

        return this.getToken(claims,user);
    } 
}
