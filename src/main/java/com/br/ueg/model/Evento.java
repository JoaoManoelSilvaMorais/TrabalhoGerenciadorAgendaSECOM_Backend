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

    // Chave primária da entidade, gerada automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título do evento.
    @Column(name = "titulo", nullable = false)
    private String titulo;

    // Data e hora de início do evento.
    @Column(name = "dataHoraInicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    // Data e hora de término do evento.
    @Column(name = "dataHoraFinal", nullable = false)
    private LocalDateTime dataHoraFinal;

    // Define se o evento exige deslocamento dos participantes.
    @Column(name = "requerDeslocamento", nullable = false)
    private Boolean requerDeslocamento;

    // Relacionamento muitos-para-muitos entre evento e profissionais.
    @ManyToMany
    @JoinTable(
        // Tabela intermediária da relação evento-profissional.
        name = "evento_profissional",
        // Coluna que referencia o id do evento na tabela intermediária.
        joinColumns = @JoinColumn(name = "evento_id"),
        // Coluna que referencia o id do profissional na tabela intermediária.
        inverseJoinColumns = @JoinColumn(name = "profissional_id")
    )
    private List<Profissional> profissionais;

    // Relacionamento muitos-para-muitos entre evento e equipamentos.
    @ManyToMany
    @JoinTable(
        // Tabela intermediária da relação evento-equipamento.
        name = "evento_equipamento",
        // Coluna que referencia o id do evento na tabela intermediária.
        joinColumns = @JoinColumn(name = "evento_id"),
        // Coluna que referencia o id do equipamento na tabela intermediária.
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<Equipamento> equipamentos;


    // Retorna o identificador do evento.
    public Long getId() {
        return id;
    }

    // Define o identificador do evento.
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o título do evento.
    public String getTitulo() {
        return titulo;
    }

    // Define o título do evento.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Retorna a data e hora de início do evento.
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    // Define a data e hora de início do evento.
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    // Retorna a data e hora de término do evento.
    public LocalDateTime getDataHoraFinal() {
        return dataHoraFinal;
    }

    // Define a data e hora de término do evento.
    public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    // Retorna se o evento requer deslocamento.
    public Boolean getRequerDeslocamento() {
        return requerDeslocamento;
    }

    // Define se o evento requer deslocamento.
    public void setRequerDeslocamento(Boolean requerDeslocamento) {
        this.requerDeslocamento = requerDeslocamento;
    }

    // Retorna a lista de profissionais associados ao evento.
    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    // Define a lista de profissionais associados ao evento.
    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    // Retorna a lista de equipamentos associados ao evento.
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    // Define a lista de equipamentos associados ao evento.
    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
