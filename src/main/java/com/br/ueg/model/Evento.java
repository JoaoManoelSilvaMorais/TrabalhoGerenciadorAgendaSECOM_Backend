package com.br.ueg.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "dataHoraInicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "dataHoraFinal", nullable = false)
    private LocalDateTime dataHoraFinal;

    @Column(name = "requerDeslocamento", nullable = false)
    private Boolean requerDeslocamento;

    @ManyToMany
    @JoinTable(
        name = "evento_profissional",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "profissional_id")
    )
    private List<Profissional> profissionais;

    @ManyToMany
    @JoinTable(
        name = "evento_equipamento",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<Equipamento> equipamentos;


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    public Boolean getRequerDeslocamento() {
        return requerDeslocamento;
    }

    public void setRequerDeslocamento(Boolean requerDeslocamento) {
        this.requerDeslocamento = requerDeslocamento;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
