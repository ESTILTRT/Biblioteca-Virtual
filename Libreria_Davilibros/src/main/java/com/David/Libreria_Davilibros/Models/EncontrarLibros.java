package com.David.Libreria_Davilibros.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EncontrarLibros(
        @JsonAlias("count") Integer encontrados,
        @JsonAlias("results") List<LibroRecord> libros
) {
}
