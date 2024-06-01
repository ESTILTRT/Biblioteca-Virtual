package com.David.Libreria_Davilibros.Service;

import com.David.Libreria_Davilibros.Models.Autor;
import com.David.Libreria_Davilibros.Models.AutorRecord;
import com.David.Libreria_Davilibros.Models.Libro;
import com.David.Libreria_Davilibros.Models.LibroRecord;
import com.David.Libreria_Davilibros.Repository.AutorRepository;
import com.David.Libreria_Davilibros.Repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;

public class ConvertirDatos implements IConvertirDatos {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Autor convertirAurtor(AutorRecord autoresR, AutorRepository repository){
        Autor autorExistente = repository.findByNombre(autoresR.nombre());
        Autor autor;
        if (autorExistente != null) {
            autor = autorExistente;
        } else {
            autor = new Autor(autoresR);
            repository.save(autor);
        }
        return autor;
    }

    public Libro convertirLibro(LibroRecord libroR, LibroRepository repository){
        Libro libroExistente = repository.findByTitulo(libroR.titulo());
        Libro libro;
        if (libroExistente != null) {
            return null;
        } else {
            libro = new Libro(libroR);
            return libro;
        }
    }

    public static boolean convertirInt(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
