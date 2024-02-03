package com.luan.client.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteDTO {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private Boolean ativo;
	private Boolean aberto;

	private CozinhaDTO cozinha;
	private EnderecoDTO endereco;

}
