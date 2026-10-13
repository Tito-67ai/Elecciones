package com.app.votacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.votacion.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByEleccionId(Long eleccionId);

    List<Voto> findByCandidatoId(Long candidatoId);

    boolean existsByEleccionIdAndDniCiudadano(Long eleccionId, String dniCiudadano);

    long countByCandidatoId(Long candidatoId);
}

