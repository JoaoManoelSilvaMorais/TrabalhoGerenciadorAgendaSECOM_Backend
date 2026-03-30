package com.br.ueg.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamento")
public class Equipamento {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "dataAquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @Column(name = "isDisponivel", nullable = false)
    private Boolean isDisponivel;

    @Column(name = "pesoGramas", nullable = false)
    private Integer pesoGramas;

    @ManyToMany(mappedBy = "equipamentos")
    @JsonIgnore
    private List<Evento> eventos;

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Boolean getIsDisponivel() {
        return isDisponivel;
    }

    public void setIsDisponivel(Boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }

    public Integer getPesoGramas() {
        return pesoGramas;
    }

    public void setPesoGramas(Integer pesoGramas) {
        this.pesoGramas = pesoGramas;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    
}
