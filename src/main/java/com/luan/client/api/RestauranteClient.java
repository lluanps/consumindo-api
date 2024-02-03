package com.luan.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.luan.client.api.exception.ClientApiException;
import com.luan.client.dto.RestauranteDTO;
import com.luan.client.dto.RestauranteResumoDTO;
import com.luan.client.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {
	
	private static final String RESOURCE_PATH = "/restaurantes";

	private String url = "http://localhost:8080";
	
	private RestTemplate restTemplate;
	
	public List<RestauranteResumoDTO> listar() {
		try {
			URI resourceUri =  URI.create(url + RESOURCE_PATH); // cria uri
			
			RestauranteResumoDTO[] restaurnates = restTemplate
					.getForObject( // pega(get) o objeto da classe retornado ela
							resourceUri, // busca os dados por essa uri
							RestauranteResumoDTO[].class); // transforma o json em array de RestauranteResumoDTO
			
			return Arrays.asList(restaurnates); // converte o array em lista e retorna
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
	public RestauranteDTO adicionar(RestauranteInput restauranteInput) {
		var resourceUri = URI.create(url + RESOURCE_PATH);
		
		try {
			return restTemplate
					.postForObject(resourceUri, restauranteInput, RestauranteDTO.class);
		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
}
