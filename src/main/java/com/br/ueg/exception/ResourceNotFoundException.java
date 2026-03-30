package com.br.ueg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Define que, quando essa exceção for lançada, a API deve retornar HTTP 404 (Not Found).
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{

	// Identificador de versão da classe para serialização.
	private static final long serialVersionUID = 1L;
	
	// Construtor que recebe a mensagem de erro e repassa para RuntimeException.
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	

}