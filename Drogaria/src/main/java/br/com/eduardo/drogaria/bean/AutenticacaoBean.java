package br.com.eduardo.drogaria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.com.eduardo.drogaria.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class AutenticacaoBean implements Serializable{
	private Usuario usuario;

	public void inicar() {
		
	}
}
