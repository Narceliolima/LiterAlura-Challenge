package br.com.exemplo.LiterAlura.main;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.exemplo.LiterAlura.model.Author;
import br.com.exemplo.LiterAlura.model.Book;
import br.com.exemplo.LiterAlura.model.GutendexSearchData;
import br.com.exemplo.LiterAlura.model.Languages;
import br.com.exemplo.LiterAlura.repository.BookRepository;
import br.com.exemplo.LiterAlura.service.APIConsumer;
import br.com.exemplo.LiterAlura.service.JsonDataConverter;

public class MainMenu {

	private final String BASE_NAME_FOR_SEARCH = "Search Camp";
	private final String URL = "https://gutendex.com/books/?search="+BASE_NAME_FOR_SEARCH;
	private APIConsumer consumer = new APIConsumer(URL, BASE_NAME_FOR_SEARCH);
	private JsonDataConverter converter = new JsonDataConverter();
	private BookRepository repository;
	
	public MainMenu(BookRepository repository) {
		this.repository = repository;
	}
	
	public void showMenu() {
		
		int option = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println(GUIText.MENU_1);
			
			try {
				option = Integer.valueOf(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero valido");
				option = 99999;
			}
			System.out.println(GUIText.MENU_INIT);
			
			switch(option) {
			
				case 0:
					//Finaliza
					System.out.println("Finalizando...");
					break;
			
				case 1:
					searchBookPerTitle(sc);
					break;
					
				case 2:
					listRegisteredBooks(sc);
					break;
					
				case 3:
					listRegisteredAuthors(sc);
					break;
					
				case 4:
					listAuthorsInYear(sc);
					break;
					
				case 5:
					searchBookInLanguage(sc);
					break;
					
				case 6:
					listStatistics();
					break;
			
				case 7:
					getTop10MoreDownloadedBook();
					break;
					
				case 8:
					searchAuthorPerName(sc);
					break;
					
				case 9:
					listAuthorIsNotLivingInYear(sc);
					break;
					
				case 99999:
					
					break;
		
				default:
					System.out.println("Insira uma opcao valida");
					break;
			
			}
			
		} while (option!=0);
		
		sc.close();
	}

	private void searchBookPerTitle(Scanner sc) {
		
		System.out.println("Digite o nome do livro");
		String dataToSearch = sc.nextLine();
		
		String data = consumer.getData(dataToSearch);
		storeBookInDB(data);
	}
	
	private void listRegisteredBooks(Scanner sc) {
		
		List<Book> books = repository.findAll();
		books.forEach(book -> GUIText.printBook(book));
	}
	
	private void listRegisteredAuthors(Scanner sc) {
		
		List<Author> authors = repository.findAllAuthors();
		authors.forEach(author -> GUIText.printAuthor(author, repository.findByAuthor(author)));
	}
	
	private void listAuthorsInYear(Scanner sc) {
		
		System.out.println("Insira o ano");
		int year;
		
		try {
			year = Integer.valueOf(sc.nextLine());
			List<Author> authors = repository.findAuthorsBornInYear(year);
			authors.forEach(author -> GUIText.printAuthor(author, repository.findByAuthor(author)));
		} catch (NumberFormatException e) {
			System.out.println("Ano invalido, retornando ao menu...");
		}
	}
	
	private void searchBookInLanguage(Scanner sc) {
		
		System.out.println("Selecione um Idioma (Insira as iniciais)");
		List<String> languages = repository.getAllLanguages();
		languages.forEach(language -> {
			System.out.println(language+" - "+Languages.getPtLanguage(Languages.valueOf(language.toUpperCase())));
		});
		String language = sc.nextLine();
		List<Book>books = repository.findByLanguagesContainingIgnoreCase(language);
		books.forEach(book -> GUIText.printBook(book));
	}
	
	private void listStatistics() {

		List<Book> books = repository.findAll();
		DoubleSummaryStatistics estatistic = books.stream().collect(Collectors.summarizingDouble(Book::getDownloadCount));
		List<Book> moreDownloaded = new ArrayList<>();
		List<Book> lessDownloaded = new ArrayList<>();
		books.forEach(book -> {
			if(book.getDownloadCount()==estatistic.getMax()) {
				moreDownloaded.add(book);
			}
			if(book.getDownloadCount()==estatistic.getMin()) {
				lessDownloaded.add(book);
			}
		});
		System.out.println(GUIText.MENU_INIT);
		System.out.println("Estatisticas");
		System.out.printf("Livros mais baixados: ");
		moreDownloaded.forEach(book -> System.out.printf("[%s]",book.getTitle()));
		System.out.printf("\nLivros menos baixados: ");
		lessDownloaded.forEach(book -> System.out.printf("[%s]",book.getTitle()));
		System.out.printf("\nMedia de Download dos Livros: %.0f ",estatistic.getAverage());
		System.out.printf("\nTotal de Downloads: %.0f \n",estatistic.getSum());
		System.out.println(GUIText.MENU_END);
	}

	private void getTop10MoreDownloadedBook() {
		
		List<Book> books = repository.findTop10ByOrderByDownloadCountDesc();
		books.forEach(book -> GUIText.printBook(book));
	}
	
	private void searchAuthorPerName(Scanner sc) {
		
		System.out.println("Insira o nome do autor");
		Author author = repository.findAuthorByName(sc.nextLine());
		GUIText.printAuthor(author, repository.findByAuthor(author));
	}
	
	private void listAuthorIsNotLivingInYear(Scanner sc) {
		
		System.out.println("Insira o ano");
		int year;
		
		try {
			year = Integer.valueOf(sc.nextLine());
			List<Author> authors = repository.findAuthorIsNotLivingInYear(year);
			authors.forEach(author -> GUIText.printAuthor(author, repository.findByAuthor(author)));
		} catch (NumberFormatException e) {
			System.out.println("Ano invalido, retornando ao menu...");
		}
	}

	private void storeBookInDB(String data) {
		
		Book book = getBookfromData(data);
		
		if(book==null) {
			System.out.println("Este livro nao existe, pesquise novamente");
		}
		else {
			repository.save(book);
			System.out.println("Livro "+book.getTitle()+" encontrado e guardado com sucesso!");
		}
	}

	private Book getBookfromData(String data) {
		
		GutendexSearchData gutendexSearchData = converter.getJsonData(data, GutendexSearchData.class);
		
		return gutendexSearchData.count()==0 ? null : new Book(gutendexSearchData.booksData().get(0), repository);
	}
}
