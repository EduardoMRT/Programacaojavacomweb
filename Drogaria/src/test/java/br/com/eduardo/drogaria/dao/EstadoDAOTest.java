package br.com.eduardo.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore //serve para ignorar o salvar()
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	
	@Test
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
	
		//Retorna a quantidade de registros
		System.out.println("Total de Registros Encontrados: " + resultado.size());
		
		/*for:, percorre a lista 1 por 1 jogando, o Estado é um cursor
		 * então ele recebe o primeiro, segundo... até terminar*/
		for(Estado estado : resultado) {
			System.out.println(estado.getCodigo()+" - "+estado.getSigla() + " - " + estado.getNome());
		}
	}
	
}
