package com.app.votacion.dto;

public record CandidatoModificarDTO(
    String nombre,
    String apellido,
    String partido,
    Long eleccionId
) {}

