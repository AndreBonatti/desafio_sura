package br.com.springbootapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private long idCliente;

	@Column(nullable = false, unique = true)
	private String nome;

	@Pattern(regexp = ".+@.+\\..+", message = "email inválido")
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column
	private String rua;

	@Column
	private String cidade;

	@Column
	private String bairro;

	@Column
	private String cep;

	@Column
	private String estado;

	public Cliente() {
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
		@Override
	public String toString() {
		return String.format(
				"Cliente [idCliente=%s, nome=%s, email=%s, senha=%s, rua=%s, cidade=%s, bairro=%s, cep=%s, estado=%s]",
				idCliente, nome, email, senha, rua, cidade, bairro, cep, estado);
	}

}
