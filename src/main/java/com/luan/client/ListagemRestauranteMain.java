package com.luan.client;

import org.springframework.web.client.RestTemplate;

import com.luan.client.api.RestauranteClient;
import com.luan.client.api.exception.ClientApiException;

public class ListagemRestauranteMain {
	
	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient restauranteClient = new RestauranteClient(
					"http://localhost:8080",
					restTemplate);
			
			System.out.println(restauranteClient);
			
			restauranteClient.listar().stream()
			.forEach(restaurante -> System.out.println(restaurante));
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}

}
