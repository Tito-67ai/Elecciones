package com.app.votacion.dto;

import java.time.LocalDate;

import com.app.votacion.model.Eleccion;

public record EleccionSalidaDTO(
    Long id,
    String nombre,
    String descripcion,
    LocalDate fecha,
    boolean activa
) {

    public static EleccionSalidaDTO from(Eleccion eleccion) {
        return new EleccionSalidaDTO(
                eleccion.getId(),
                eleccion.getNombre(),
                eleccion.getDescripcion(),
                eleccion.getFecha(),
                eleccion.isActiva()
        );
    }
}

