package br.com.eduardo.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Fabricante;
import br.com.eduardo.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(new Long("1"));

		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 Comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

		System.out.println("Produto salvo com sucesso");
	}

	@Test
	@Ignore
	public void buscar() {
		// Primeiro passar o código para busca
		Long codigo = 1L;
		// Cria DAO
		ProdutoDAO produtoDAO = new ProdutoDAO();
		// Busca por cod
		Produto produto = produtoDAO.buscar(codigo);
		// Tratamento de erro para produto null
		if (produto == null) {
			System.out.println("Produto não encontrado");
		} else {
			System.out.println("\nCódigo do Produto: " + produto.getCodigo() + " - " + produto.getDescricao()
					+ "\nQuantidade: " + produto.getQuantidade() + " | Preço: " + produto.getPreco() + "\nFabricante: "
					+ produto.getFabricante());
		}
	}

	@Test
	@Ignore
	public void listar() {
		// Cria DAO
		ProdutoDAO produtoDAO = new ProdutoDAO();
		// Listagem de produtos
		List<Produto> resultado = produtoDAO.listar();

		// Cria FOR para percorrer 1 a 1
		for (Produto produto : resultado) {
			// Imprime os dados
			System.out.println("\nCódigo do Produto: " + produto.getCodigo() + " - " + produto.getDescricao()
					+ "\nQuantidade: " + produto.getQuantidade() + " | Preço: " + produto.getPreco() + "\nFabricante: "
					+ produto.getFabricante());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		// Passar código para exclusão
		Long codigo = 1L;
		// Cria DAO
		ProdutoDAO produtoDAO = new ProdutoDAO();
		// Buscar produto por código
		Produto produto = produtoDAO.buscar(codigo);
		// Faz o tratamento de erro
		if (produto == null) {
			System.out.println("Produt não existe");
		} else {
			// Puxar o exluir do DAO
			produtoDAO.excluir(produto);
			// Exibe o produto excluído
			System.out.println("Produto excluído: " + produto.getDescricao() + " - Fabricante: "
					+ produto.getFabricante().getDescricao());
		}
	}

	@Test
	@Ignore
	public void editar() {
		// Passar código para edição
		// Código do produto
		Long codigoProduto = 2L;

		// Código do Fabricante
		Long codigoFabricante = 3L;

		// Cria o DAO do Produto
		ProdutoDAO produtoDAO = new ProdutoDAO();

		// Cria o DAO do Fabricante
		FabricanteDAO fabricanteDAO = new FabricanteDAO();

		// Busca o produto pelo código
		Produto produto = produtoDAO.buscar(codigoProduto);

		// Busca o fabricante pelo código
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);

		// Tratamento de erro do tipo null
		if (produto == null) {
			System.out.println("Produto não existe");
		} else {
			// Adicionando novos atributos
			produto.setDescricao("Dicoflenaco Resinato 25mg");
			produto.setPreco(new BigDecimal("17.99"));
			produto.setQuantidade(new Short("2"));

			// Puxa o fabricante e insere no produto
			produto.setFabricante(fabricante);

			// Utiliza o método de edição GenericDAO
			produtoDAO.editar(produto);

			// Exibe na tela
			System.out.println("Produto alterado com sucesso!");
			System.out.println(
					"Produto: " + produto.getDescricao() + " - Fabricante: " + produto.getFabricante().getDescricao());
		}
	}
}