package com.app.patron.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.patron.model.Ciudadano;

public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {

    List<Ciudadano> findByActivoTrue();

    Optional<Ciudadano> findByIdAndActivoTrue(Long id);

    Optional<Ciudadano> findByDni(String dni);

    boolean existsByDni(String dni);
}

