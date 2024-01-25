package br.com.eduardo.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Cidade;
import br.com.eduardo.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigo_Cidade = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo_Cidade);
		PessoaDAO pessoaDAO = new PessoaDAO();

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Exemplo");
		pessoa.setCpf("123.456.789-10");
		pessoa.setRg("256.456.54-5");
		pessoa.setRua("Rua exemplo");
		pessoa.setNumero(new Short("238"));
		pessoa.setBairro("Bairro exemplo");
		pessoa.setCep("18900-000");
		pessoa.setComplemento("Complement");
		pessoa.setTelefone("149999-9999");
		pessoa.setCelular("14999999999");
		pessoa.setEmail("email@example.com");
		pessoa.setCidade(cidade);
		pessoaDAO.salvar(pessoa);
		System.out.println(pessoa.getCidade().getNome());

	}

	@Test
	@Ignore
	public void buscar() {
		// Código de busca
		Long codigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Pessoa não existe");
		} else {
			System.out.println("Pessoa: " + pessoa.getNome() + " - " + pessoa.getCpf() + "\nCel: " + pessoa.getCelular()
					+ " - Email: " + pessoa.getEmail());
		}
	}

	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		for (Pessoa pessoa : resultado) {
			System.out.println("\nPessoa: " + pessoa.getNome() + " - " + pessoa.getCpf() + "\nCel: "
					+ pessoa.getCelular() + " - Email: " + pessoa.getEmail());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Pessoa não existe");
		} else {
			pessoaDAO.excluir(pessoa);
			System.out.println(
					"A pessoa COD: " + pessoa.getCodigo() + " Nome: " + pessoa.getNome() + " foi excluída com sucesso");
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoPessoa = 2L;
		Long codigoCidade = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		CidadeDAO cidadeDAO = new CidadeDAO();

		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		if ((pessoa == null) || (cidade == null)) {
			System.out.println("Pessoa ou cidade não existe");
		} else {
			pessoa.setNome("TESTE");
			pessoa.setCidade(cidade);
			pessoaDAO.editar(pessoa);
			System.out.println("Nova pessoa: "+pessoa.getCodigo()+" - "+pessoa.getNome()+", Cidade: "+pessoa.getCidade().getNome());
		}
	}
}
