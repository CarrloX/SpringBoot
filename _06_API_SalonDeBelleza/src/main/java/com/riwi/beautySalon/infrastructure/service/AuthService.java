package com.riwi.beautySalon.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.beautySalon.api.dto.request.LoginReq;
import com.riwi.beautySalon.api.dto.request.RegisterReq;
import com.riwi.beautySalon.api.dto.response.AuthResp;
import com.riwi.beautySalon.domain.entity.User;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infrastructure.abstract_services.IAuthService;
import com.riwi.beautySalon.infrastructure.helpers.JwtService;
import com.riwi.beautySalon.utils.enums.Role;
import com.riwi.beautySalon.utils.enums.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Override
    public AuthResp login(LoginReq request) {
        return null;
    }

    @Override
    public AuthResp register(RegisterReq request) {
        // 1.validar que el username no exista
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("este usuario ya esta registrado");
        }

        // 2.construimos el nuevo usuario
        User user = User.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .role(Role.CLIENT)
                .build();

        // 3.guardar el nuevo usuario en la DB
        this.userRepository.save(user);

        return AuthResp.builder()
                .message("se registro exitosamente")
                .token(this.jwtService.getToken(user))
                .build();
    }

    private User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName)
                .orElse(null);
    }

}
