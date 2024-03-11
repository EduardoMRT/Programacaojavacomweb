package br.com.eduardo.drogaria.domain;

import javax.persistence.Entity;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;


@SuppressWarnings("serial")
@Entity
@Access(AccessType.FIELD)
public class Estado extends GenericDomain {
	// WorkSpaces

	@Column(length = 2)
	private String sigla;

	@Column(length = 50)
	private String nome;

	private Cidade cidade;
	
	private Pessoa pessoa;
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
