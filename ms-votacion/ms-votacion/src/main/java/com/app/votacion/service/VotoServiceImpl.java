package com.app.votacion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.app.votacion.client.CiudadanoClient;
import com.app.votacion.config.RabbitMQConfig;
import com.app.votacion.dto.CiudadanoDTO;
import com.app.votacion.exception.RecursoNoEncontradoException;
import com.app.votacion.exception.VotoDuplicadoException;
import com.app.votacion.model.Candidato;
import com.app.votacion.model.Voto;
import com.app.votacion.repository.CandidatoRepository;
import com.app.votacion.repository.EleccionRepository;
import com.app.votacion.repository.VotoRepository;
import com.app.votacion.senders.VotoEvent;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final CandidatoRepository candidatoRepository;
    private final EleccionRepository eleccionRepository;
    private final CiudadanoClient ciudadanoClient;
    private final RabbitTemplate rabbitTemplate;

    public VotoServiceImpl(
            VotoRepository votoRepository,
            CandidatoRepository candidatoRepository,
            EleccionRepository eleccionRepository,
            CiudadanoClient ciudadanoClient,
            RabbitTemplate rabbitTemplate) {
        this.votoRepository = votoRepository;
        this.candidatoRepository = candidatoRepository;
        this.eleccionRepository = eleccionRepository;
        this.ciudadanoClient = ciudadanoClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Voto registrarVoto(Voto voto) {

        voto.setId(null);
        voto.setFechaHora(LocalDateTime.now());

        validarEleccion(voto.getEleccionId());
        validarCandidato(voto.getCandidatoId(), voto.getEleccionId());
        validarCiudadano(voto.getDniCiudadano());

        if (votoRepository.existsByEleccionIdAndDniCiudadano(
                voto.getEleccionId(), voto.getDniCiudadano())) {
            throw new VotoDuplicadoException(
                    "El ciudadano con dni " + voto.getDniCiudadano()
                            + " ya emitio su voto en la eleccion " + voto.getEleccionId());
        }

        Voto guardado = votoRepository.save(voto);

        notificarVoto(guardado);

        return guardado;
    }

    @Override
    public List<Voto> listarVotos() {
        return votoRepository.findAll();
    }

    @Override
    public List<Voto> listarPorEleccion(Long eleccionId) {
        return votoRepository.findByEleccionId(eleccionId);
    }

    @Override
    public long contarPorCandidato(Long candidatoId) {
        return votoRepository.countByCandidatoId(candidatoId);
    }

    private void validarEleccion(Long eleccionId) {

        if (eleccionId == null || eleccionRepository.findByIdAndActivaTrue(eleccionId).isEmpty()) {
            throw new RecursoNoEncontradoException(
                    "Eleccion no encontrada con id: " + eleccionId);
        }
    }

    private void validarCandidato(Long candidatoId, Long eleccionId) {

        Candidato candidato = candidatoRepository.findByIdAndActivoTrue(candidatoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Candidato no encontrado con id: " + candidatoId));

        if (!candidato.getEleccionId().equals(eleccionId)) {
            throw new RecursoNoEncontradoException(
                    "El candidato " + candidatoId + " no pertenece a la eleccion " + eleccionId);
        }
    }

    private void validarCiudadano(String dniCiudadano) {

        CiudadanoDTO ciudadano = ciudadanoClient.obtenerPorDni(dniCiudadano);

        if (ciudadano == null) {
            throw new RecursoNoEncontradoException(
                    "Ciudadano no encontrado con dni: " + dniCiudadano);
        }
    }

    private void notificarVoto(Voto voto) {

        VotoEvent evento = new VotoEvent();
        evento.setIdVoto(voto.getId());
        evento.setIdEleccion(voto.getEleccionId());
        evento.setIdCandidato(voto.getCandidatoId());
        evento.setDniCiudadano(voto.getDniCiudadano());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                evento
        );
    }
}

