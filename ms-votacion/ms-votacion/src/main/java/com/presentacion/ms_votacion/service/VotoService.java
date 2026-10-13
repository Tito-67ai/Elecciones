package com.presentacion.ms_votacion.service;

import java.util.List;

import com.presentacion.ms_votacion.model.Voto;

public interface VotoService {

    Voto registrarVoto(Voto voto);

    List<Voto> listarVotos();

    List<Voto> listarPorEleccion(Long eleccionId);

    long contarPorCandidato(Long candidatoId);
}
