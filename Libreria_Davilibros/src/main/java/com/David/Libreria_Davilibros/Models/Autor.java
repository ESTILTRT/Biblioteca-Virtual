package com.David.Libreria_Davilibros.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;
    @Transient
    private Integer cantidadDeLibros;

    public Autor(){}

    public Autor(AutorRecord a) {
        this.nombre = a.nombre();
        this.fechaNacimiento = a.nacimiento();
        this.fechaMuerte = a.muerte();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Integer getCantidadDeLibros() {
        return cantidadDeLibros;
    }

    public void setCantidadDeLibros(Integer cantidadDeLibros) {
        this.cantidadDeLibros = cantidadDeLibros;
    }

    @Override
    public String toString() {
        return "\nNombre=" + nombre.replace(",","") +
                ", Nacimiento=" + fechaNacimiento +
                ", Murio=" + fechaMuerte;
    }
}
