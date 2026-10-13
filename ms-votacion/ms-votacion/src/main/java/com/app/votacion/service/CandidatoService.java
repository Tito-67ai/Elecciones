package com.app.votacion.service;

import java.util.List;
import java.util.Optional;

import com.app.votacion.dto.CandidatoModificarDTO;
import com.app.votacion.model.Candidato;

public interface CandidatoService {

    Candidato altaCandidato(Candidato candidato);

    List<Candidato> listarCandidatos();

    List<Candidato> listarPorEleccion(Long eleccionId);

    Optional<Candidato> obtenerPorId(Long id);

    Optional<Candidato> modificarCandidato(Long id, CandidatoModificarDTO dto);

    boolean eliminarCandidato(Long id);
}

