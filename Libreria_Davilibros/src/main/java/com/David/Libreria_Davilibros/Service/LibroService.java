package com.David.Libreria_Davilibros.Service;

import com.David.Libreria_Davilibros.Models.Autor;
import com.David.Libreria_Davilibros.Models.EncontrarLibros;
import com.David.Libreria_Davilibros.Models.Libro;
import com.David.Libreria_Davilibros.Models.LibroRecord;
import com.David.Libreria_Davilibros.Repository.AutorRepository;
import com.David.Libreria_Davilibros.Repository.LibroRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

public class LibroService {
    ConvertirDatos convertirDatos = new ConvertirDatos();
    String urlBusqueda = "https://gutendex.com/books/?search=";

    List<Autor> autores;


    public EncontrarLibros obtenerDatos(String nombre){
        String json = ConectarAPI.obtenerDatos(urlBusqueda + URLEncoder.encode(nombre));
        EncontrarLibros datos = convertirDatos.convertirDatos(json, EncontrarLibros.class);
        return datos;
    }

    public void guardarLibro(LibroRecord libroAGGuardar, AutorRepository autorRepository, LibroRepository libroRepository){
        Autor autor = convertirDatos.convertirAurtor(libroAGGuardar.autores().get(0),autorRepository);
        Libro libro = convertirDatos.convertirLibro(libroAGGuardar,libroRepository);
        if (libro == null){
            System.out.println("Libro ya esta guardado...");
        }
        else {
            libro.setAutor(autor);
            libroRepository.save(libro);
            System.out.println(libro);
        }
    }

    public LibroRecord buscarLibro(String nombre){
        var librosEncontrados = obtenerDatos(nombre);
        if (librosEncontrados.encontrados() <= 0){
            return null;
        }
        else {
            var libro = librosEncontrados.libros().get(0);
            System.out.println("Libro encontrado!");
            return libro;
        }
    }


}
