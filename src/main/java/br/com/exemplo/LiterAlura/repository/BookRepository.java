package br.com.exemplo.LiterAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.exemplo.LiterAlura.model.Author;
import br.com.exemplo.LiterAlura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT author FROM Book book JOIN book.author author WHERE author.name LIKE %:name%")
	Author findAuthorByName(String name);

	@Query("SELECT author FROM Book book JOIN book.author author")
	List<Author> findAllAuthors();
	
	List<Book> findByAuthor(Author author);

	@Query("SELECT author FROM Book book JOIN book.author author WHERE author.birthYear <= :year AND author.deathYear >= :year")
	List<Author> findAuthorsBornInYear(Integer year);

	@Query("SELECT DISTINCT(book.languages) FROM Book book")
	List<String> getAllLanguages();
	
	List<Book> findByLanguagesContainingIgnoreCase(String language);

	List<Book> findTop10ByOrderByDownloadCountDesc();

	@Query("SELECT author FROM Book book JOIN book.author author WHERE author.deathYear <= :year")
	List<Author> findAuthorIsNotLivingInYear(Integer year);
}
