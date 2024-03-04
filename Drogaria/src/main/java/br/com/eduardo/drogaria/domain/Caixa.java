package br.com.eduardo.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Access(AccessType.FIELD)
public class Caixa extends GenericDomain{
	
	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorAbertura;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
}

