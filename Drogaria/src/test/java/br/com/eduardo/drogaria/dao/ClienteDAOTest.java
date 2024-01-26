package br.com.eduardo.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.Pessoa;

@SuppressWarnings("unused")
public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("26/01/2024"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Cliente salvo com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
		
		for (Cliente cliente : resultado) {
			System.out.println("\nPessoa: " + cliente.getPessoa().getNome() + " - " + cliente.getDataCadastro() + " - "
					+ cliente.getCodigo()+" Cod P:"+cliente.getPessoa().getCodigo());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);
		
		if(cliente == null) {
			System.out.println("Cliente não existente");
		}else {
			clienteDAO.excluir(cliente);
			System.out.println("Cliente excluído com sucesso!");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		if(cliente == null) {
			System.out.println("Cliente não existe");
		}else if(pessoa == null){
			System.out.println("Pessoa não existe");
		}else {
			pessoa.setNome("Alterado Teste");
			cliente.setLiberado(false);
			cliente.setPessoa(pessoa);
			clienteDAO.editar(cliente);
			
			System.out.println("Pessoa"+cliente.getPessoa().getNome()+" - "+cliente.getLiberado());
		}
	}
}
