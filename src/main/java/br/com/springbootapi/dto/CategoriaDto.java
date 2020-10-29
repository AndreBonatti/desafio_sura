package br.com.springbootapi.dto;

import java.io.Serializable;

public class CategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
