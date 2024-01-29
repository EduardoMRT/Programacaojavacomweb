package br.com.eduardo.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.domain.Estado;

//Tempo de vida - 3 tipos:
//request - objeto só vive por um clique
//view - existe enquanto você esta na tela
//session - objetos ficam vivos durante todo o tempo de vida da aplicação

@ManagedBean
@ViewScoped
public class EstadoBean {
	private Estado estado;
	
	
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo() {
		estado = new Estado();
	}
	public void salvar() {
		Messages.addGlobalInfo("Nome: "+estado.getNome() + " Sigla: "+estado.getSigla());
	}
}

