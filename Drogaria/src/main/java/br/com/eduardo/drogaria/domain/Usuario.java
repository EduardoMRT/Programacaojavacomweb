package br.com.eduardo.drogaria.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Access(AccessType.FIELD)
public class Usuario extends GenericDomain{
	@Column(nullable = false, length = 128)
	private String senha;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	
	@Transient //diz que esse método só serve para formatação
	public String getTipoFormatado(){
		String tipoFormatado = null;
			if(tipo == 'A') {
				tipoFormatado = "Administrador";
			}else if(tipo == 'B') {
				tipoFormatado = "Balconista";
			}else if(tipo == 'G'){
				tipoFormatado = "Gerente";
			}
			
			return tipoFormatado;
	}
	
	public String getAtivoFormatado() {
		String ativoFormatado = null;
		if(ativo == true) {
			ativoFormatado = "Sim";
		}else {
			ativoFormatado = "Não";
		}
		return ativoFormatado;
	}
	
}
