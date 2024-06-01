package com.David.Libreria_Davilibros.Models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idiomas idioma;
    private Integer descargas;
    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    public Libro(){}

    public Libro(LibroRecord libro) {
        this.titulo = libro.titulo();
        this.idioma = Idiomas.fromAPI(libro.lenguajes().get(0));
        this.descargas = libro.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return titulo;
    }

    public void setNombre(String nombre) {
        this.titulo = nombre;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autores) {
        this.autor = autores;
    }

    @Override
    public String toString() {
        return "\n***********************************"+
                "\nLibro=" + titulo +
                "\nIdioma=" + idioma +
                "\nDescargas=" + descargas +
                "\nAutor=" + autor.getNombre().replace(",", "")+
                "\n***********************************\n";
    }
}
