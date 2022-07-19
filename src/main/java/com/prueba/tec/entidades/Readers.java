package com.prueba.tec.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "readers")
public class Readers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_reader", nullable = false)
	private Long pkReader;
	
	@Column(name = "name", length = 8, nullable = false)
	@Size(min = 1, max = 8, message = "El campo name tiene un tama�o maximo de 8 caracteres")
	private String name;

	public Long getPkReader() {
		return pkReader;
	}

	public void setPkReader(Long pkReader) {
		this.pkReader = pkReader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
