package br.com.eduardo.drogaria.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Access(AccessType.FIELD)
public class Usuario extends GenericDomain{
	@Column(nullable = false, length = 32)
	private String senha;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}
	
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
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
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
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
