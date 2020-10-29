package br.com.springbootapi.dto;

import java.io.Serializable;

public class PedidoItemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProduto;
	private String produtoName;
	private Integer quantidade;
	private Float valor;
	private Float subtotal;

	public PedidoItemDto() {
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getProdutoName() {
		return produtoName;
	}

	public void setProdutoName(String produtoName) {
		this.produtoName = produtoName;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return String.format("PedidoItemDto [idProduto=%s, produtoName=%s, quantidade=%s, valor=%s, subtotal=%s]",
				idProduto, produtoName, quantidade, valor, subtotal);
	}

}
