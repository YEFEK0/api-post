package com.apipost.api_post.DTOs;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDTO {

    @NotBlank(message = "El autor es obligatorio")
    private String author;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 500, message = "El contenido no puede superar los 500 caracteres")
    private String content;

    @NotNull(message = "la fecha es obligatoria")
    private LocalDateTime createdAt;
}
