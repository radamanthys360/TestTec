package com.prueba.tec.request;

import javax.validation.constraints.Size;

public class ReaderRequest {
	
	private Long pkReaderInput;
	
	@Size(min = 1, max = 8, message = "El campo name tiene un tamaño maximo de 8 caracteres")
	private String nameInput;

	public Long getPkReaderInput() {
		return pkReaderInput;
	}

	public void setPkReaderInput(Long pkReaderInput) {
		this.pkReaderInput = pkReaderInput;
	}

	public String getNameInput() {
		return nameInput;
	}

	public void setNameInput(String nameInput) {
		this.nameInput = nameInput;
	}

}
