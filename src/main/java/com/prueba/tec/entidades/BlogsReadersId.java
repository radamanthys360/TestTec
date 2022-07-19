package com.prueba.tec.entidades;

import java.io.Serializable;
import java.util.Objects;

public class BlogsReadersId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long rId;
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
	
	public BlogsReadersId() {}
	
	public BlogsReadersId(Long rId, Long bId) {
		super();
		this.rId = rId;
		this.bId = bId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bId, rId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogsReadersId other = (BlogsReadersId) obj;
		return Objects.equals(bId, other.bId) && Objects.equals(rId, other.rId);
	}
	
}
