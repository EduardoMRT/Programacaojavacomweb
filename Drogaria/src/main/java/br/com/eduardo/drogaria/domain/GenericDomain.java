package br.com.eduardo.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GenericDomain implements Serializable{
	private Long codigo;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
}
