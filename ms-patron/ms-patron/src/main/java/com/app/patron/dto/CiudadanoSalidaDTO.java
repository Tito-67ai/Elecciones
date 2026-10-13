package com.app.patron.dto;

import com.app.patron.model.Ciudadano;

public record CiudadanoSalidaDTO(
    Long id,
    String nombre,
    String apellido,
    String dni,
    String email,
    String direccion,
    boolean yaVoto
) {

    public static CiudadanoSalidaDTO from(Ciudadano ciudadano) {
        return new CiudadanoSalidaDTO(
                ciudadano.getId(),
                ciudadano.getNombre(),
                ciudadano.getApellido(),
                ciudadano.getDni(),
                ciudadano.getEmail(),
                ciudadano.getDireccion(),
                ciudadano.isYaVoto()
        );
    }
}

