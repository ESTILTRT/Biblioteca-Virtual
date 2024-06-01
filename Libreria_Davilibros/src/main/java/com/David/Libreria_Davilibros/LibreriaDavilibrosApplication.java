package com.David.Libreria_Davilibros;

import com.David.Libreria_Davilibros.Main.Principal;
import com.David.Libreria_Davilibros.Repository.AutorRepository;
import com.David.Libreria_Davilibros.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaDavilibrosApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibreriaDavilibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n¡Bienvenido a Davilibros!");
		Principal principal = new Principal(autorRepository, libroRepository);
		principal.mostrarMenu();
	}
}
