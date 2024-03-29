package com.prueba.tec.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permiso_recurso")
public class PermisoRecurso {
	
	@Id
	@Column(name = "codigo", updatable = false, nullable = false)
	private Long codigo;
	
	@Column(name="recurso", length=300)
    private String recurso;
	
	@Column(name="authorities", length=50)
    private String authorities;
	
	@Column(name="activo", length=1)
    private String activo;
	
	public PermisoRecurso() {
		// TODO Auto-generated constructor stub
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}