package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.hibernate.exception.ConstraintViolationException;
import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.domain.Estado;
import br.com.eduardo.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
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

	@PostConstruct // Sem ele não funciona
	public void listar() {
		try {
			//FabricanteDAO fabricanteDAO = new FabricanteDAO();
			//fabricantes = fabricanteDAO.listar();
			
			//CRIA UM NOVO CLIENTE
			Client cliente = ClientBuilder.newClient();
			//GUARDA O CAMINHO
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");
			//DISPARA UMA REQUISIÇÃO
			String json = caminho.request().get(String.class);
			System.out.println(json);
			
			Gson gson = new Gson();
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
		
			fabricantes = Arrays.asList(vetor);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void salvar() {
		try {
//			FabricanteDAO fabricanteDAO = new FabricanteDAO();
//			fabricanteDAO.merge(fabricante);
//
//			fabricante = new Fabricante();
//			fabricantes = fabricanteDAO.listar();
			
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");
			
			//salvar
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);
			caminho.request().post(Entity.json(json));
			
			fabricante = new Fabricante();
			
			//listar
			json = caminho.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			fabricantes = Arrays.asList(vetor);
			
			
			Messages.addGlobalInfo("Fabricante salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o fabricante");
			erro.printStackTrace();
		}
	}
	
	//others
	public void excluir(ActionEvent evento) { // serve para capturar oq foi mandado
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);
			fabricantes = fabricanteDAO.listar();
			Messages.addGlobalInfo("Fabricante Removido com Sucesso: " +fabricante.getDescricao());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o fabricante");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}

}
