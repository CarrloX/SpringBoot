package com.riwi.beautySalon.api.dto.request;

import com.riwi.beautySalon.utils.enums.RoleEmployee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReq {
    @NotBlank(message = "el nombre es requerido")
    private String firstName;
    @NotBlank(message = "el appelido es requerido")
    private String lastName;
    @Size(min = 10, max = 20, message = "el telefono debe tener entre 10 y 20 caracteres")
    private String phone;
    @Email(message = "el email no es valido")
    @Size(min = 5, max = 100, message = "el email debe tener entre 5 y 100 caracteres")
    private String email;
    @NotBlank(message = "el rol es requerido")
    private RoleEmployee role;
}
