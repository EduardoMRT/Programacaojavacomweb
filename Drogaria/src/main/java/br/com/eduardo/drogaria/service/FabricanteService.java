package br.com.eduardo.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.domain.Fabricante;


@Path("fabricante")
public class FabricanteService {
	//http://127.0.0.1:8080/Drogaria/rest/fabricante
	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");
		
		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);
		
		return json;
	}
		
		//http://127.0.0.1:8080/Drogaria/rest/fabricante/10
		//cada vez que coloca-se um patch é como se colocasse uma barra
		@GET
		@Path("{codigo}")
		public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
	
		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		
		return json;
		}
}
