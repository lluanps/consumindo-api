package com.luan.client.api.exception;

import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luan.client.dto.Problem;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Getter
	private Problem problem;
	
	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);
		
		cause.getResponseBodyAsString();
	}

	private void deserializeProblem(RestClientResponseException cause) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// usado para não retornar erro ao n adicionar propriedades necessarias
		objectMapper.registerModule(new JavaTimeModule());// resolver erro OffsetDateTime
		objectMapper.findAndRegisterModules();
		
		try {
			this.problem = objectMapper.readValue(cause.getResponseBodyAsString(), Problem.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			log.warn("Não foi possivel desserializar a resposta em um problema", e);
		}
	}
	
}
