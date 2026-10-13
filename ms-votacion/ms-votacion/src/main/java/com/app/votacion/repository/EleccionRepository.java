package com.app.votacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.votacion.model.Eleccion;

public interface EleccionRepository extends JpaRepository<Eleccion, Long> {

    List<Eleccion> findByActivaTrue();

    Optional<Eleccion> findByIdAndActivaTrue(Long id);
}

