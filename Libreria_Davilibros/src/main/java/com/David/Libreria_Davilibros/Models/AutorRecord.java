package com.David.Libreria_Davilibros.Models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorRecord(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer muerte
) {
}
