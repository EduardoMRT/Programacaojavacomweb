package br.com.eduardo.drogaria.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@Access(AccessType.FIELD)
public class Fabricante extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}