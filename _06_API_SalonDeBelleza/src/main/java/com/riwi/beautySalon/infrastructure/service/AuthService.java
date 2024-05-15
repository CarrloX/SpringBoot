package com.riwi.beautySalon.infrastructure.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riwi.beautySalon.api.dto.request.ClientRegiserReq;
import com.riwi.beautySalon.api.dto.request.EmployeeRegisterReq;
import com.riwi.beautySalon.api.dto.request.LoginReq;
import com.riwi.beautySalon.api.dto.request.RegisterReq;
import com.riwi.beautySalon.api.dto.response.AuthResp;
import com.riwi.beautySalon.domain.entity.ClientEntity;
import com.riwi.beautySalon.domain.entity.Employee;
import com.riwi.beautySalon.domain.entity.User;
import com.riwi.beautySalon.domain.repositories.ClientRepository;
import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infrastructure.abstract_services.IAuthService;
import com.riwi.beautySalon.infrastructure.helpers.JwtService;
import com.riwi.beautySalon.utils.enums.Role;
import com.riwi.beautySalon.utils.enums.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AuthResp login(LoginReq request) {
        try {
            // autenticar en la app
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(),
                            request.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("credenciales invalidas");
        }

        // si el usuario se autentico correctamente
        User user = this.findByUserName(request.getUserName());

        if (user == null) {
            throw new BadRequestException("el usuario no esta registrado");
        }

        return AuthResp.builder()
                .message("atenticado correctamente")
                .token(this.jwtService.getToken(user))
                .build();
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
                // guardar la contraseña codificada
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        // 3.guardar el nuevo usuario en la DB
        this.userRepository.save(user);

        return AuthResp.builder()
                .message("se registro exitosamente")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    // metodo para registrar un cliente
    public AuthResp registerClient(ClientRegiserReq request) {

        // validamo sque el usuario no exista
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("el usuario ya esta registrado");
        }

        // construimos el usuario

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();

        // ahora lo guardamos en la bse de datos
        User usersave = this.userRepository.save(user);

        // construimos el cliente

        ClientEntity client = ClientEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .user(usersave)
                .appointments(new ArrayList<>())
                .build();

        this.clientRepository.save(client);

        return AuthResp.builder()
                .message("cliente registrado correctamente")
                .token(this.jwtService.getToken(usersave))
                .build();
    }

    private User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName)
                .orElse(null);
    }

    @Override
    public AuthResp registerEmployee(EmployeeRegisterReq request) {

        // validamo sque el usuario no exista
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("el usuario ya esta registrado");
        }

        // construimos el usuario

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();

        // ahora lo guardamos en la bse de datos
        User usersave = this.userRepository.save(user);

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        this.employeeRepository.save(employee);

        return AuthResp.builder()
                .message("empleado registrado correctamente")
                .token(this.jwtService.getToken(usersave))
                .build();
    }

}
