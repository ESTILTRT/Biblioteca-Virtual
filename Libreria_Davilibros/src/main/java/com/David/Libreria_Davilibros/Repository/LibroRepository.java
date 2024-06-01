package com.David.Libreria_Davilibros.Repository;

import com.David.Libreria_Davilibros.Models.Idiomas;
import com.David.Libreria_Davilibros.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Libro findByTitulo(String titulo);
    @Query(value ="SELECT COUNT(libros.id) FROM libros WHERE autor_id = :id" , nativeQuery = true)
    Integer cantidadDeLibros(long id);
    List<Libro> findByIdioma(Idiomas idioma);
    List<Libro> findTop10ByOrderByDescargasDesc();
}
