package com.prueba.tec.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BlogsReadersId.class)
@Table(name = "blogs_readers")
public class BlogsReaders {
	
	@Id
	@Column(name = "r_id", nullable = false)
	private Long rId;
	
	@Id
	@Column(name = "b_id", nullable = false)
	private Long bId;

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public Long getbId() {
		return bId;
	}

	public void setbId(Long bId) {
		this.bId = bId;
	}

}
