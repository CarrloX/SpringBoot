package com.riwi.beautySalon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    
    //define un bean para password encoder
    //este encoder es utilizado para encirptar y desencriptar las contraseñas en la aplicacion
    //retorna una instancia de BCryptPaswwordEnconder, en un metodo de hcifrado o hash fuertemente
    //y ampliamente utilizado
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
