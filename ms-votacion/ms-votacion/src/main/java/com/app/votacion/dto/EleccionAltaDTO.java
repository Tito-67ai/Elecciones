package com.app.votacion.dto;

import java.time.LocalDate;

public record EleccionAltaDTO(
    String nombre,
    String descripcion,
    LocalDate fecha
) {}

