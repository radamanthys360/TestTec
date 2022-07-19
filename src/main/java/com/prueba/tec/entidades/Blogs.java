package com.prueba.tec.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "blogs")
public class Blogs {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_blog", nullable = false)
	private Long pkBlog;
	
	@Column(name = "title", length = 50, nullable = false)
	@Size(min = 1, max = 50, message = "El campo title tiene un tama�o maximo de 50 caracteres")
	private String title;
	
	@Column(name = "description", length = 4000, nullable = false)
	@Size(min = 1, max = 4000, message = "El campo description tiene un tama�o maximo de 4000 caracteres")
	private String description;

	public Long getPkBlog() {
		return pkBlog;
	}

	public void setPkBlog(Long pkBlog) {
		this.pkBlog = pkBlog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
