package com.app.votacion.dto;

import java.time.LocalDate;

public record EleccionModificarDTO(
    String nombre,
    String descripcion,
    LocalDate fecha,
    Boolean activa
) {}

