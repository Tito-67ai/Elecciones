package com.app.votacion.service;

import java.util.List;

import com.app.votacion.model.Voto;

public interface VotoService {

    Voto registrarVoto(Voto voto);

    List<Voto> listarVotos();

    List<Voto> listarPorEleccion(Long eleccionId);

    long contarPorCandidato(Long candidatoId);
}

