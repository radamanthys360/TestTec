package com.prueba.tec.response;

public class ReaderPerBlogResponse {
	
	private Long rIdRes;
	
	private Long bIdRes;

	private String nameReaderRes;
	
	private String nameBlogRes;

	public Long getrIdRes() {
		return rIdRes;
	}

	public void setrIdRes(Long rIdRes) {
		this.rIdRes = rIdRes;
	}

	public Long getbIdRes() {
		return bIdRes;
	}

	public void setbIdRes(Long bIdRes) {
		this.bIdRes = bIdRes;
	}

	public String getNameReaderRes() {
		return nameReaderRes;
	}

	public void setNameReaderRes(String nameReaderRes) {
		this.nameReaderRes = nameReaderRes;
	}

	public String getNameBlogRes() {
		return nameBlogRes;
	}

	public void setNameBlogRes(String nameBlogRes) {
		this.nameBlogRes = nameBlogRes;
	}
	
}
