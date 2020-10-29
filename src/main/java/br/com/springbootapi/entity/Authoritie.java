package br.com.springbootapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authoritie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String authority;

	public Authoritie() {
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
