package br.com.eduardo.drogaria.dao;

import org.junit.Test;

import br.com.eduardo.drogaria.domain.Cidade;
import br.com.eduardo.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	public void salvar() {
		// Primeiro você pesquisa os pais para depois
		// preencher os filhos
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(1L);

		if (estado == null) {
			System.out.println("Estado não encontrado");
		} else {
			Cidade cidade = new Cidade();
			cidade.setNome("São Pedro do Turvo");
			cidade.setEstado(estado);
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);
			System.out.print(cidade.getCodigo() + " - " + cidade.getNome() + " - " + estado.getSigla());
		}

	}
}
