package com.David.Libreria_Davilibros.Main;

import com.David.Libreria_Davilibros.Models.Autor;
import com.David.Libreria_Davilibros.Models.Idiomas;
import com.David.Libreria_Davilibros.Models.Libro;
import com.David.Libreria_Davilibros.Repository.AutorRepository;
import com.David.Libreria_Davilibros.Repository.LibroRepository;
import com.David.Libreria_Davilibros.Service.ConvertirDatos;
import com.David.Libreria_Davilibros.Service.LibroService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private LibroService libroiService = new LibroService();

    private AutorRepository autorRepository;
    private LibroRepository libroRepository;

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                \n******************************
                  Que desea hacer? 
                        1- Buscar un libro.
                        2- Consultar Libros
                        3- Consultar Autores.
                        
                        0- salir
                ******************************""");
           String validar = scanner.nextLine();

            if(ConvertirDatos.convertirInt(validar) == false){
                System.out.println("La opción que diste es invalida");
                continue;
            }
            opcion = Integer.parseInt(validar);
            switch (opcion){
                case 0:
                    System.out.println("El programa se esta cerrando...");
                    break;
                case 1:
                    optenerLibro();
                    break;
                case 2:
                    consultaLibros();
                    break;
                case 3:
                    consultaAutor();
                    break;
                default:
                    System.out.println("La opción elegida no existe...");
                    break;
            }
        }
    }
    private void consultaAutor(){
        while (true){
            System.out.println("""
                \n****************************************
                De que forma desea consultar?
                    1- Nombre
                    2- Lista de autores vivos por fecha
                    
                    0- volver
                ****************************************""");
            String validar = scanner.nextLine();
            if(ConvertirDatos.convertirInt(validar) == false){
                System.out.println("La opción que diste es invalida");
                continue;
            }
            int opcion = Integer.parseInt(validar);
            switch (opcion) {
                case 0:
                    mostrarMenu();
                    break;
                case 1:
                    consultarPorNombreAutor();
                    break;
                case 2:
                    consultarPorFecha();
                    break;
                default:
                    System.out.println("La opción elegida no existe...");
                    break;
            }
        }
    }
    private void consultaLibros(){
        while (true){
            System.out.println("""
                \n****************************************
                De que forma desea consultar?
                    1- Todos los libros guerdados
                    2- Por idioma
                    3- Mas descargados
                    
                    0- volver
                ****************************************""");
            String validar = scanner.nextLine();
            if(ConvertirDatos.convertirInt(validar) == false){
                System.out.println("La opción que diste es invalida");
                continue;
            }
            int opcion = Integer.parseInt(validar);
            switch (opcion) {
                case 0:
                    mostrarMenu();
                    break;
                case 1:
                    todosLosLibros();
                    break;
                case 2:
                    porIdioma();
                    break;
                case 3:
                    topLibros();
                    break;
                default:
                    System.out.println("La opción elegida no existe...");
                    break;
            }
        }
    }

    private void optenerLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar.");
        var nombreLibro = scanner.nextLine();
        var libroEncontrado = libroiService.buscarLibro(nombreLibro);
        if (libroEncontrado == null){
            System.out.println("Libro no encontrado...");
        }
        else{
            libroiService.guardarLibro(libroEncontrado,autorRepository, libroRepository);
        }
    }

    private void consultarPorNombreAutor(){
        System.out.println("Escriba el nombre del autor que desea buscar");
        String nombreAutor = scanner.nextLine();
        Optional<Autor> autorAEncontrar = autorRepository.buscarPorNombre(nombreAutor);
        if (autorAEncontrar.isPresent()){
            Autor autor = autorAEncontrar.get();
            autor.setCantidadDeLibros(libroRepository.cantidadDeLibros(autor.getId()));
            System.out.println("Nombre: "+ autor.getNombre()+
                    "\nAño de nacimiento: "+autor.getFechaNacimiento()+
                    "\nAño de muerte: "+autor.getFechaMuerte()+
                    "\nCantidad de encontrados: "+autor.getCantidadDeLibros());
        } else {
            System.out.println("El nombre digitado no se encontró...");
        }
    }

    private void consultarPorFecha(){
        System.out.println("Escriba en el año en el que desea buscar");
        var ano = scanner.nextLine();
        if (ConvertirDatos.convertirInt(ano)){
            var fecha = Integer.parseInt(ano);
            List<Autor> encontrados = autorRepository.buscarPorAno(fecha);
            if (encontrados.size() == 0){
                System.out.println("No se encontró ningún autor dentro de la fecha que diste vivo...");
            } else {
              encontrados.stream()
                      .forEach(System.out::println);
            }
        }
        else {
            System.out.println("Datos ingresados no válidos para el proceso...");
        }
    }

    private void todosLosLibros(){
        libroRepository.findAll().forEach(System.out::println);
    }

    private void porIdioma(){
        System.out.println("Digite el idioma por el que desea consultar");
        var opcion = scanner.nextLine();
        Idiomas idioima = Idiomas.Buscar(opcion);
        List<Libro> encontrados = libroRepository.findByIdioma(idioima);
        if (encontrados.size() == 0){
            System.out.println("No se encontraron libros en ese idioma...");
        } else {
            encontrados.forEach(System.out::println);
        }
    }
    private void topLibros(){
        List<Libro> top = libroRepository.findTop10ByOrderByDescargasDesc();
        top.forEach(l -> System.out.println("Titulo: "+l.getNombre()+ ", Descargas: "+l.getDescargas()));
    }
}
