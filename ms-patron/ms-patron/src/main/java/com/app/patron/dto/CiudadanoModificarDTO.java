package com.app.patron.dto;

public record CiudadanoModificarDTO(
    String nombre,
    String apellido,
    String dni,
    String email,
    String direccion
) {}

