package com.cenfotec.deporte.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class IMC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double imc;
    private double estatura;
    private double peso;
    private Date fechaCalculo;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Atleta atleta;

    //constructor
    public IMC(double imc, double peso, Date fechaCalculo, Atleta atleta) {
        this.imc = imc;
        this.peso = peso;
        this.fechaCalculo = fechaCalculo;
        this.atleta = atleta;
    }

    public IMC() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }
}
