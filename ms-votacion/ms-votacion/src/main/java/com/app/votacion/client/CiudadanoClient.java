package com.app.votacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.votacion.dto.CiudadanoDTO;

@FeignClient(name = "ms-patron")
public interface CiudadanoClient {

    @GetMapping("/ciudadanos/dni/{dni}")
    CiudadanoDTO obtenerPorDni(@PathVariable("dni") String dni);
}

