package br.com.springbootapi.dto;

import java.io.Serializable;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;

	private String email;

	private String senha;

	private String rua;

	private String cidade;

	private String bairro;

	private String cep;

	private String estado;

	public ClienteDto() {
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
				"Cliente [ nome=%s, email=%s, senha=%s, rua=%s, cidade=%s, bairro=%s, cep=%s, estado=%s]", 
				nome, email, senha, rua, cidade, bairro, cep, estado);
	}

}
