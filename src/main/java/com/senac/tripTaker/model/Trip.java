package com.senac.tripTaker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String local;
    private int qtdPessoas;

    @Column(name = "guia_responsavel")
    private String guiaResponsavel;

    @Column(name = "valor_unitario")
    private double valorUnitario;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_final")
    private String dataFinal;

    private String descricao;
    private String image;

    public Trip() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public String getGuiaResponsavel() {
        return guiaResponsavel;
    }

    public void setGuiaResponsavel(String guiaResponsavel) {
        this.guiaResponsavel = guiaResponsavel;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", local='" + local + '\'' +
                ", qtdPessoas=" + qtdPessoas +
                ", guiaResponsavel='" + guiaResponsavel + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                ", descricao='" + descricao + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
