package com.prueba.tec.request;

public class ReaderBlogRequest {
	
	private Long readerId;
	
	private Long blogId;

	public Long getReaderId() {
		return readerId;
	}

	public void setReaderId(Long readerId) {
		this.readerId = readerId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	
}
