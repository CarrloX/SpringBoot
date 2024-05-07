package com.riwi.beautySalon.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentReq {
    @NotBlank(message = "la fecha y hora es requerida")
    private LocalDateTime dateTime;
    @Min(value = 10, message = "la durcion no puede ser menor a 10 minutos")
    @Max(value = 720,message = "la duracion no puede ser mayor a 12 horas")
    private Integer duration;
    private String comments;
    @NotNull(message = "el id del cliente es obligatorio")
    private Long clienteId;
    @NotNull(message = "el id del servicio es obligatorio")
    private Long serviceId;
    @NotNull(message = "el id del empleado es obligatorio")
    private Long employeeId;
}
