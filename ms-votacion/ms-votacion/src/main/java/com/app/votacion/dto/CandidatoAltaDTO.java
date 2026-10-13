package com.app.votacion.dto;

public record CandidatoAltaDTO(
    String nombre,
    String apellido,
    String partido,
    Long eleccionId
) {}

