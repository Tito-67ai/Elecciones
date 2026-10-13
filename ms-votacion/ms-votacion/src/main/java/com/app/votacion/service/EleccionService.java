package com.app.votacion.service;

import java.util.List;
import java.util.Optional;

import com.app.votacion.dto.EleccionModificarDTO;
import com.app.votacion.model.Eleccion;

public interface EleccionService {

    Eleccion altaEleccion(Eleccion eleccion);

    List<Eleccion> listarElecciones();

    Optional<Eleccion> obtenerPorId(Long id);

    Optional<Eleccion> modificarEleccion(Long id, EleccionModificarDTO dto);

    boolean eliminarEleccion(Long id);
}

