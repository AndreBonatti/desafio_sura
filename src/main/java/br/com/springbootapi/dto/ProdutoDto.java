package br.com.springbootapi.dto;

import java.io.Serializable;

public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCategoria;
	private String produto;
	private Double preco;
	private int quantidade;
	private String descricao;
	private String foto;
	
	public ProdutoDto() {
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return String.format("ProdutoDto [idCategoria=%s, produto=%s, preco=%s, quantidade=%s, descricao=%s, foto=%s]",
				idCategoria, produto, preco, quantidade, descricao, foto);
	}

}
