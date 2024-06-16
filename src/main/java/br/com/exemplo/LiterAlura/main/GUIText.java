package br.com.exemplo.LiterAlura.main;

import java.util.List;

import br.com.exemplo.LiterAlura.model.Author;
import br.com.exemplo.LiterAlura.model.Book;

public class GUIText {
	
	public static final String MENU_1 = """
			========================//========================
			Consulta Livros
			
			O que deseja fazer? (Digite o numero equivalente no menu)
			-------------------------------------------------
			|1 - Buscar Livro Pelo Titulo			|
			|2 - Listar Livros Registrados			|
			|3 - Listar Autores Registrados			|
			|4 - Listar Autores vivos em um determinado Ano	|
			|5 - Listar Livros em um determinado Idioma	|
			|6 - Listar Estatisticas			|
			|7 - Top 10 Livros mais Baixados		|
			|8 - Buscar Autor pelo Nome			|
			|9 - Listar Autores que ja Faleceram		|
			|						|
			|						|
			|0 - Sair					|
			-------------------------------------------------
			========================//========================""";
	
	public static final String MENU_INIT = """
			========================//========================
			-------------------------------------------------""";
	public static final String MENU_END = """
			-------------------------------------------------
			========================//========================""";
	
	public static void printBook(Book book) {
		System.out.println("------------------- LIVRO -----------------------");
		System.out.println("Titulo: "+book.getTitle());
		System.out.println("Autor: "+book.getAuthor().getName());
		System.out.println("Idioma: "+book.getLanguages());
		System.out.println("Downloads: "+book.getDownloadCount());
		System.out.println("-------------------------------------------------");
	}

	public static void printAuthor(Author author, List<Book> books) {
		System.out.println("------------------- AUTOR -----------------------");
		System.out.println("Nome: "+author.getName());
		System.out.println("Data de Nascimento: "+author.getBirthYear());
		System.out.println("Data de Falecimento: "+author.getDeathYear());
		System.out.printf("Obras: ");
		books.forEach(book -> System.out.printf("[%s]",book.getTitle()));
		System.out.println();
		System.out.println("-------------------------------------------------");
	}
}
