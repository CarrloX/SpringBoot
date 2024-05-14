package com.riwi.beautySalon.infrastructure.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 1.obtener el token
        final String token = getTokenFromRequest(request);

        // si el token es nulo entonces seguir con los filtros de spring
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2.obtener el usuario de token
        String userName = this.jwtService.getUsernameFrontToken(token);

        // si no lo encuentra en el contexto de spring security
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // obtener el usuario por usename a partir del repositorio
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (this.jwtService.isTokenValid(token, userDetails)) {
                // crear la autenticacion y la registramos en el contexto de seguridad de spring
                var authToken = new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());

                // asignar detalles de la autenticacion basados en la fuente de la solicitud

                // setDetails:establece detalles adicionales de la autenticacion como la
                // direccion IP y la sesion de donde se realiza la solicitud
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // registra el token de autenticacion en el contexto de seguridad efectivamente
                // autenticando al usuario para la duracion de la solicitud
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);

    }

    // metood para obtener el token del request
    public String getTokenFromRequest(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // si el token no esta vacio y ademas inicia con la palabra Bearer
        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
