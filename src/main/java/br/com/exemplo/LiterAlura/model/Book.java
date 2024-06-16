package br.com.exemplo.LiterAlura.model;

import br.com.exemplo.LiterAlura.repository.BookRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(unique = true, nullable = false)
	private String title;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Author author;
	private String languages;
	private Integer downloadCount;
	
	public Book() {}

	public Book(BookData bookData, BookRepository repository) {
		this.title = bookData.title();
		Author author = repository.findAuthorByName(bookData.authors().get(0).name());
		this.author = author==null ? new Author(bookData.authors().get(0)) : author;
		this.languages = bookData.languages().get(0);
		this.downloadCount = bookData.downloadCount();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public String getLanguages() {
		return languages;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	};
}
