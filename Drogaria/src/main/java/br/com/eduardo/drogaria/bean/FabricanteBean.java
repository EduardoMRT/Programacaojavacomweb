package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
public class FabricanteBean implements Serializable{
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public void listar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		}catch (RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionarios");
			erro.printStackTrace();
		}
	}
}
