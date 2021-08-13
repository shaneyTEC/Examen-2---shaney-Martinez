package com.cenfotec.deporte.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //atributos
    //persona
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeNacimiento;

    // direccion
    private String direccionExacta;
    private String provincia;
    private String canton;
    private String distrito;

    //contacto
    private String email;
    private long telefono;

    //atleta
    private String deporte;
    private char rama;

    //IMC
    private double estatura;
    private double peso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "atleta")
    private Set<IMC> imcs;

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public char getRama() {
        return rama;
    }

    public void setRama(char rama) {
        this.rama = rama;
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

    public Set<IMC> getImcs() {
        return imcs;
    }

    public void setImcs(IMC imcs) {
        this.imcs.add(imcs);
    }
    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }
}
