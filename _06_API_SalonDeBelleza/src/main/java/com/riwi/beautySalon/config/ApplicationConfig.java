package com.riwi.beautySalon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.riwi.beautySalon.domain.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {
    // inyectamos el repositorio del usuario
    @Autowired
    private final UserRepository userRepository;

    // AuthenticationManager permite el manejo del usuario en toda la app
    // define un bean de tipo AuthenticationManager
    // utiliza la configuracion de spring security para obtener una onfiguracion
    // ya preparada (la que viene por defecto)

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }

    // este metodo crea y configura un DaoAuthenticationProvider , que es una de las
    // implementaciones mas comunes para proverr datos a nuestra app, de esta forma
    // guardaremos las credenciales del usuario
    // guardaremos toda la inforamcion y el tipo de cifrado que tiene su contraseña

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        authenticationProvider.setUserDetailsService(this.userDetailsService());

        return authenticationProvider();
    }

    // este servicio es utilizado por springboot security para cargar detalles
    // del usuario durante la autenticacion

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // define un bean para password encoder
    // este encoder es utilizado para encirptar y desencriptar las contraseñas en la
    // aplicacion
    // retorna una instancia de BCryptPaswwordEnconder, en un metodo de hcifrado o
    // hash fuertemente
    // y ampliamente utilizado
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
