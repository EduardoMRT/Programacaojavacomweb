package br.com.eduardo.drogaria.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
@Access(AccessType.FIELD)
public class Email extends GenericDomain {

	@Column(nullable = false)
	private int idemail;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	public int getIdemail() {
		return idemail;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}
}
