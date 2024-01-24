package br.com.eduardo.drogaria.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
@Access(AccessType.FIELD)
public class Cidade extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado; // estado = chave estrangeira, framework a FK é um objeto do tipo que ele esta
							// ligado

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
