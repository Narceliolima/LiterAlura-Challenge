package br.com.exemplo.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.exemplo.LiterAlura.main.MainMenu;
import br.com.exemplo.LiterAlura.repository.BookRepository;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner{
	
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new MainMenu(repository).showMenu();
	}
}
