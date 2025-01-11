package com.alura.miprimerforo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es requerido")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 a 100 caracteres")
    private String titulo;

    @NotBlank(message = "El mensaje es requerido")
    @Size(min = 5, max = 200, message = "El mensaje debe ser entre 5 a 200 caracteres")
    private String mensaje;

    @NotNull(message = "La fecha de creación es requerida")
    private LocalDateTime fechaCreacion;

    @NotBlank(message = "El status no puede ser vacio")
    private String status;

    @NotBlank(message = "El autor es requerido")
    private String autor;

    @NotBlank(message = "El curso es requerido")
    private String curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El título es requerido") @Size(min = 2, max = 100, message = "El título debe tener entre 2 a 100 caracteres") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El título es requerido") @Size(min = 2, max = 100, message = "El título debe tener entre 2 a 100 caracteres") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "El mensaje es requerido") @Size(min = 5, max = 200, message = "El mensaje debe ser entre 5 a 200 caracteres") String getMensaje() {
        return mensaje;
    }

    public void setMensaje(@NotBlank(message = "El mensaje es requerido") @Size(min = 5, max = 200, message = "El mensaje debe ser entre 5 a 200 caracteres") String mensaje) {
        this.mensaje = mensaje;
    }

    public @NotNull(message = "La fecha de creación es requerida") LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(@NotNull(message = "La fecha de creación es requerida") LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public @NotBlank(message = "El status no puede ser vacio") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "El status no puede ser vacio") String status) {
        this.status = status;
    }

    public @NotBlank(message = "El autor es requerido") String getAutor() {
        return autor;
    }

    public void setAutor(@NotBlank(message = "El autor es requerido") String autor) {
        this.autor = autor;
    }

    public @NotBlank(message = "El curso es requerido") String getCurso() {
        return curso;
    }

    public void setCurso(@NotBlank(message = "El curso es requerido") String curso) {
        this.curso = curso;
    }
}
