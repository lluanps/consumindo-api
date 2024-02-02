package com.luan.client;

import org.springframework.web.client.RestTemplate;

import com.luan.client.api.RestauranteClient;

public class ListagemRestauranteMain {
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		
		RestauranteClient restauranteClient = new RestauranteClient(
				"http://localhost:8080",
				restTemplate);
		
		System.out.println(restauranteClient);
		
		restauranteClient.listar().stream()
			.forEach(restaurante -> System.out.println(restaurante));
	}

}
