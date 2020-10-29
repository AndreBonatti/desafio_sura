package br.com.springbootapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private long idProduto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria", insertable = false, updatable = false)
	private Categoria categoria;
	
	@Column(name = "id_categoria")
	@NotNull(message = "O código do categoria não pode ser nulo.")
	private long idCategoria;

	@Column(nullable = false)
	private String produto;

	@Column(nullable = false)
	private Double preco;

	
	@Column(nullable = false)
	private int quantidade;

	@Column(nullable = false)
	private String descricao;

	@Column
	private String foto;

	public Produto() {
	}

	public Produto(long idProduto) {
		this.idProduto = idProduto;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return String.format(
				"Produto [idProduto=%s, categoria=%s, idCategoria=%s, produto=%s, preco=%s, quantidade=%s, descricao=%s, foto=%s]",
				idProduto, categoria, idCategoria, produto, preco, quantidade, descricao, foto);
	}

}
