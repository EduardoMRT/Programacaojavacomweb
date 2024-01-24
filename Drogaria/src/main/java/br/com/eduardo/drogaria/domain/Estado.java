package br.com.eduardo.drogaria.domain;

import javax.persistence.Entity;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
public class Estado extends GenericDomain {
	//Não funciona o @Column(length = n) no MySQL 8, foi feito manualmente no WorkSpaces
	//@Column(length = 2)
	private String sigla;
	
	//@Column(length = 50)
	private String nome;

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
