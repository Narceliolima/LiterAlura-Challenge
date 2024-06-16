package br.com.exemplo.LiterAlura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexSearchData(@JsonAlias("results") List<BookData> booksData,
								@JsonAlias("count") Integer count) {

}
