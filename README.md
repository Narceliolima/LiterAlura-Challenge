# LiterAlura | Desafio de Projeto Allura

Aplicação para pesquisas de livros, o usuário busca o livro em uma API, os dados adquiridos pela API são salvos em um histórico local da qual o usuário poderá acessar informações mais detalhadas dos livros, dos autores e estatísticas gerais.

A interação com o programa é feito de modo textual via console, o usuário tem acesso a um menu de opção, insira o numero disponibilizado no menu e em seguida siga as instruções necessárias para realizar a atividade escolhida:

```
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
========================//========================
```

Esse projeto é uma proposta de desafio lançado ao decorrer do meu curso na plataforma da Allura.
Projeto feito puramente em Java utilizando Spring Boot.

## ⚙ Requisitos

 - [Biblioteca do google para manipulação de JSON](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) 
 - [Sqlite JDBC](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)
 - [Hibernate Community Dialects](https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-community-dialects)
 - [Spring JPA](https://spring.io/projects/spring-data-jpa)

## 🖇Referência

 - [Class HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
 - [Class HttpRequest](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html)
 - [Interface HttpResponse](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.html)
 - [API para pesquisa de Livros Gutendex](https://gutendex.com/)
