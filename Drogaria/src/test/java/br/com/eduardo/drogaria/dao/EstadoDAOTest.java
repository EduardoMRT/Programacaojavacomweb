package br.com.eduardo.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore // serve para ignorar o salvar()
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		// Retorna a quantidade de registros
		System.out.println("Total de Registros Encontrados: " + resultado.size());

		/*
		 * for:, percorre a lista 1 por 1 jogando, o Estado é um cursor então ele recebe
		 * o primeiro, segundo... até terminar
		 */
		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L; // o L é pq é Long
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L; // o L é pq é Long
		// Inicia o estado DAO
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getCodigo() + " - " + estado.getNome());
		}
	}

	@Test
	public void editar() {
		Long codigo = 5L; // o L é pq é Long
		// Inicia o estado DAO
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - Antes: ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

			estado.setNome("Amazonas");
			estado.setSigla("AM");

			estadoDAO.editar(estado);
			System.out.println("Registro editado - Depois: ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
}
