package com.app.votacion.dto;

public record VotoAltaDTO(
    Long eleccionId,
    Long candidatoId,
    String dniCiudadano
) {}

