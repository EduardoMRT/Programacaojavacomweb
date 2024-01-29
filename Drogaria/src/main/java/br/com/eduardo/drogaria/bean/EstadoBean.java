package br.com.eduardo.drogaria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.EstadoDAO;
import br.com.eduardo.drogaria.domain.Estado;

//Tempo de vida - 3 tipos:
//request - objeto só vive por um clique
//view - existe enquanto você esta na tela
//session - objetos ficam vivos durante todo o tempo de vida da aplicação

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable{
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
		try {
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		
		novo();
		
		Messages.addGlobalInfo("Estado Salvo com Sucesso");
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
}

