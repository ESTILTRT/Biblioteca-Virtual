package com.David.Libreria_Davilibros.Repository;

import com.David.Libreria_Davilibros.Models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombre%")
    Optional<Autor> buscarPorNombre(String nombre);

    @Query("Select a from Autor a where a.fechaNacimiento <= :fecha and a.fechaMuerte >= :fecha")
    List<Autor> buscarPorAno(Integer fecha);
}
