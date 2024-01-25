package br.com.eduardo.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Cidade;
import br.com.eduardo.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
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
			System.out.println(cidade.getCodigo() + " - " + cidade.getNome() + " - " + estado.getSigla());
		}
	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();

		for (Cidade cidade : resultado) { // Varre toda a estrutura de dados, 1 a 1
			System.out.println("\nCódigo da Cidade:" + cidade.getCodigo() + " - " + cidade.getNome()
					+ "\nCodigo do Estado:" + cidade.getEstado().getCodigo() + " - " + cidade.getEstado().getNome()
					+ " - " + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Nenhuma cidade encontrada");
		} else {
			System.out.println("\nCódigo da Cidade:" + cidade.getCodigo() + " - " + cidade.getNome()
					+ "\nCodigo do Estado:" + cidade.getEstado().getCodigo() + " - " + cidade.getEstado().getNome()
					+ " - " + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Cidade não encontrada");
		} else {
			cidadeDAO.excluir(cidade);
			System.out.println("Cidade Removida: " + cidade.getCodigo() + " - " + cidade.getNome() + " - "
					+ cidade.getEstado().getNome());
		}
	}
}
