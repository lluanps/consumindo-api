package com.luan.client.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteResumoDTO {
	
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaDTO cozinha;

}
