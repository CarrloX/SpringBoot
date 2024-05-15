package com.riwi.beautySalon.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    @NotBlank(message = "el username es requerido")
    @Size(min = 3, max = 150, message = "el nombre del usuaro debe tener entre 3 y 150 caracteres")
    private String userName;
    @NotBlank(message = "la contraseña es requerida")
    @Size(min = 6, max = 150, message = "la contraseña debe tener entre 6 y 150 caracteres")
    // @pattern (regex = "expresion regular")
    private String password;
}
