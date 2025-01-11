package com.alura.miprimerforo.service;

import com.alura.miprimerforo.domain.Topico;
import com.alura.miprimerforo.domain.repository.TopicoRepository;
import com.alura.miprimerforo.dto.TopicoCreateDTO;
import com.alura.miprimerforo.dto.TopicoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicoService {
    private final TopicoRepository topicoRepository;

    @Autowired
    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public List<TopicoDTO> listarTodos() {
        return topicoRepository.findAll().stream()
                .map(topico -> new TopicoDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    public TopicoDTO obtenerPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Este Tópico no fue encontrado"));
        return new TopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }

    public TopicoDTO crear(TopicoCreateDTO topicoCreate) {
        if (topicoRepository.existsByTituloAndMensaje(topicoCreate.titulo(), topicoCreate.mensaje())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Esté tópico ya existe"
            );
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoCreate.titulo());
        topico.setMensaje(topicoCreate.mensaje());
        topico.setStatus(topicoCreate.status());
        topico.setAutor(topicoCreate.autor());
        topico.setCurso(topicoCreate.curso());
        topico.setFechaCreacion(LocalDateTime.now());

        Topico topicoGuardado = topicoRepository.save(topico);
        return new TopicoDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getFechaCreacion(),
                topicoGuardado.getStatus(),
                topicoGuardado.getAutor(),
                topicoGuardado.getCurso()
        );
    }

    public TopicoDTO actualizar(Long id, TopicoCreateDTO topicoCreate) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No fue encontrado el tópico"));

        topico.setTitulo(topicoCreate.titulo());
        topico.setMensaje(topicoCreate.mensaje());
        topico.setStatus(topicoCreate.status());
        topico.setAutor(topicoCreate.autor());
        topico.setCurso(topicoCreate.curso());

        Topico topicoActualizado = topicoRepository.save(topico);
        return new TopicoDTO(
                topicoActualizado.getId(),
                topicoActualizado.getTitulo(),
                topicoActualizado.getMensaje(),
                topicoActualizado.getFechaCreacion(),
                topicoActualizado.getStatus(),
                topicoActualizado.getAutor(),
                topicoActualizado.getCurso()
        );
    }

    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}
