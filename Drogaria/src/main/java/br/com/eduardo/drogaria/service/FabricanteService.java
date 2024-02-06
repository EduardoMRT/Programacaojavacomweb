package br.com.eduardo.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.domain.Fabricante;

//http://127.0.0.1:8080/Drogaria/rest/fabricante
@Path("fabricante")
public class FabricanteService {
	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");
		
		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);
		
		return json;
	}
}
