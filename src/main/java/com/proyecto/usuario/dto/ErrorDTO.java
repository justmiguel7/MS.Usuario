package com.proyecto.usuario.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private HttpStatus status;
    private String error;
    private String message;
}
