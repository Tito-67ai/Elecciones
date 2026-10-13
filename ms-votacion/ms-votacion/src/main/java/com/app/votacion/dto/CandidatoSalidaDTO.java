package com.app.votacion.dto;

import com.app.votacion.model.Candidato;

public record CandidatoSalidaDTO(
    Long id,
    String nombre,
    String apellido,
    String partido,
    Long eleccionId
) {

    public static CandidatoSalidaDTO from(Candidato candidato) {
        return new CandidatoSalidaDTO(
                candidato.getId(),
                candidato.getNombre(),
                candidato.getApellido(),
                candidato.getPartido(),
                candidato.getEleccionId()
        );
    }
}

