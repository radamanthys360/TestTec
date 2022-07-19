package com.prueba.tec.request;

import javax.validation.constraints.Size;

public class BlogRequest {
	
	private Long pkBlogInput;
	
	@Size(min = 1, max = 100, message = "El campo title tiene un tama�o maximo de 100 caracteres")
	private String titleInput;
	
	@Size(min = 1, max = 100, message = "El campo description tiene un tama�o maximo de 100 caracteres")
	private String descriptionInput;

	public Long getPkBlogInput() {
		return pkBlogInput;
	}

	public void setPkBlogInput(Long pkBlogInput) {
		this.pkBlogInput = pkBlogInput;
	}

	public String getTitleInput() {
		return titleInput;
	}

	public void setTitleInput(String titleInput) {
		this.titleInput = titleInput;
	}

	public String getDescriptionInput() {
		return descriptionInput;
	}

	public void setDescriptionInput(String descriptionInput) {
		this.descriptionInput = descriptionInput;
	}
	

}
