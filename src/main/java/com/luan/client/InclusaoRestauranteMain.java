package com.luan.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.luan.client.api.RestauranteClient;
import com.luan.client.api.exception.ClientApiException;
import com.luan.client.dto.RestauranteDTO;
import com.luan.client.input.CidadeIdInput;
import com.luan.client.input.CozinhaIdInput;
import com.luan.client.input.EnderecoInput;
import com.luan.client.input.RestauranteInput;

public class InclusaoRestauranteMain {

	  public static void main(String[] args) {
	    try {
	      var restTemplate = new RestTemplate();
	      var restauranteClient = new RestauranteClient(
	          "http://localhost:8080", restTemplate);

	      var cozinha = new CozinhaIdInput();
	      cozinha.setId(1L);

	      var cidade = new CidadeIdInput();
	      cidade.setId(1L);

	      var endereco = new EnderecoInput();
	      endereco.setCidade(cidade);
	      endereco.setCep("8810000");
	      endereco.setLogradouro("Rua Floripa");
	      endereco.setNumero("777");
	      endereco.setBairro("Centro");

	      var restaurante = new RestauranteInput();
	      restaurante.setNome("Comida Regional");
	      restaurante.setTaxaFrete(new BigDecimal(5.5));
	      restaurante.setCozinha(new CozinhaIdInput());
	      restaurante.setCozinha(cozinha);
	      restaurante.setEndereco(endereco);

	      RestauranteDTO restauranteModel = restauranteClient.adicionar(restaurante);

	      System.out.println(restauranteModel);
	    } catch (ClientApiException e) {
	      if (e.getProblem() != null) {
	        System.out.println(e.getProblem().getUserMessage());
	        
	        e.getProblem().getObjects().stream()
	          .forEach(p -> System.out.println("- " + p.getUserMessage()));
	        
	      } else {
	        System.out.println("Erro desconhecido");
	        e.printStackTrace();
	      }
	    }
	  }
	}   