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
@Table(name = "profissional")
public class Profissional {

    // Chave primária da entidade, gerada automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do profissional.
    @Column(name = "nome", nullable = false)
    private String nome;

    // Data de admissão do profissional.
    @Column(name = "dataAdmissao", nullable = false)
    private LocalDate dataAdmissao;

    // Indica se o profissional atua como videomaker.
    @Column(name = "isVideomaker", nullable = false)
    private Boolean isVideomaker;

    // Carga horária semanal do profissional.
    @Column(name = "cargaHorariaSemanal", nullable = false)
    private Integer cargaHorariaSemanal;

    // Relacionamento muitos-para-muitos com eventos.
    // mappedBy informa que o lado dono da relação está na entidade Evento.
    @ManyToMany(mappedBy = "profissionais")
    // Evita referência circular durante a serialização para JSON.
    @JsonIgnore
    private List<Evento> eventos;


    // Retorna o identificador do profissional.
    public Long getId() {
        return id;
    }

    // Define o identificador do profissional.
    public void setId(Long id) {
        this.id = id;
    }

    // Retorna o nome do profissional.
    public String getNome() {
        return nome;
    }

    // Define o nome do profissional.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a data de admissão.
    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    // Define a data de admissão.
    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    // Retorna se o profissional é videomaker.
    public Boolean getIsVideomaker() {
        return isVideomaker;
    }

    // Define se o profissional é videomaker.
    public void setIsVideomaker(Boolean isVideomaker) {
        this.isVideomaker = isVideomaker;
    }

    // Retorna a carga horária semanal.
    public Integer getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    // Define a carga horária semanal.
    public void setCargaHorariaSemanal(Integer cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    // Retorna os eventos associados ao profissional.
    public List<Evento> getEventos() {
        return eventos;
    }

    // Define os eventos associados ao profissional.
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

   

}
