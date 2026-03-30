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
    // Chave primária da tabela, gerada automaticamente pelo banco.
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Modelo do equipamento.
    @Column(name = "modelo", nullable = false)
    private String modelo;

    // Data de aquisição do equipamento.
    @Column(name = "dataAquisicao", nullable = false)
    private LocalDate dataAquisicao;

    // Indica se o equipamento está disponível para uso.
    @Column(name = "isDisponivel", nullable = false)
    private Boolean isDisponivel;

    // Peso do equipamento em gramas.
    @Column(name = "pesoGramas", nullable = false)
    private Integer pesoGramas;

    // Relacionamento muitos-para-muitos com eventos.
    // mappedBy indica que o lado dono da relação está na entidade Evento.
    @ManyToMany(mappedBy = "equipamentos")
    // Evita referência circular na serialização JSON.
    @JsonIgnore
    private List<Evento> eventos;

    // Retorna o identificador do equipamento.
    public Long getId() {
        return id;
    }

    // Define o identificador do equipamento.
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o modelo do equipamento.
    public String getModelo() {
        return modelo;
    }

    // Define o modelo do equipamento.
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Retorna a data de aquisição.
    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    // Define a data de aquisição.
    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    // Retorna o status de disponibilidade.
    public Boolean getIsDisponivel() {
        return isDisponivel;
    }

    // Define o status de disponibilidade.
    public void setIsDisponivel(Boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }

    // Retorna o peso em gramas.
    public Integer getPesoGramas() {
        return pesoGramas;
    }

    // Define o peso em gramas.
    public void setPesoGramas(Integer pesoGramas) {
        this.pesoGramas = pesoGramas;
    }

    // Retorna a lista de eventos relacionados ao equipamento.
    public List<Evento> getEventos() {
        return eventos;
    }

    // Define a lista de eventos relacionados ao equipamento.
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    
}
