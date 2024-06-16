package br.com.exemplo.LiterAlura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConsumer {
	
	private HttpClient client = null;
	private HttpRequest request = null;
	private HttpResponse<String> response = null;
	private String url = null;
	private String dataBaseNameForSearch = null;

	public APIConsumer(String url, String dataBaseNameForSearch) {
		this.url = url;
		this.dataBaseNameForSearch = dataBaseNameForSearch;
		this.client = HttpClient.newHttpClient();
	}
	
	public String getData(String data) {
		data = data.replaceAll(" ", "%20");
		
		this.request = HttpRequest.newBuilder().uri(URI.create(url.replace(dataBaseNameForSearch, data))).build();
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		if(response.statusCode()==404) {
			return "notfound";
		}
		else{
			return response.body();
		}
	}
}
