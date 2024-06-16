package br.com.exemplo.LiterAlura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private Integer birthYear;
	private Integer deathYear;
	
	public Author() {}

	public Author(AuthorData authorData) {
		this.name = authorData.name();
		this.birthYear = authorData.birthYear();
		this.deathYear = authorData.deathYear();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public Integer getDeathYear() {
		return deathYear;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public void setDeathYear(Integer deathYear) {
		this.deathYear = deathYear;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear + "]";
	}
}
