package com.luan.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.luan.client.dto.RestauranteResumoDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {
	
	private static final String RESOURCE_PATH = "/restaurantes";

	private String url = "http://localhost:8080";
	
	private RestTemplate restTemplate;
	
	public List<RestauranteResumoDTO> listar() {
		
		URI resourceUri =  URI.create(url + RESOURCE_PATH); // cria uri

		RestauranteResumoDTO[] restaurnates = restTemplate
				.getForObject( // pega(get) o objeto da classe retornado ela
						resourceUri, // busca os dados por essa uri
						RestauranteResumoDTO[].class); // transforma o json em array de RestauranteResumoDTO
		
		return Arrays.asList(restaurnates); // converte o array em lista e retorna
	}
	
	
}