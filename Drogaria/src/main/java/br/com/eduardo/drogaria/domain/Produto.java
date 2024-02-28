package br.com.eduardo.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Produto extends GenericDomain{
	@Column(length = 80 , nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fabricante fabricante;

	@Transient
	private String caminho;
	
}