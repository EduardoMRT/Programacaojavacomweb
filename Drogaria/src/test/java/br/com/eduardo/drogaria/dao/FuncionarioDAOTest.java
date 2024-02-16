package br.com.eduardo.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Funcionario;
import br.com.eduardo.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		Funcionario funcionario = new Funcionario();
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("16/02/2024"));
		funcionario.setCarteiraTrabalho("CARTEIRATRAB");
		funcionario.setPessoa(pessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);

		System.out.println("Cliente salvo com sucesso!: "+funcionario.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		for (Funcionario funcionario : resultado) {
			System.out.println("\nFuncionario: " + funcionario.getPessoa().getNome() +" - "
					+ funcionario.getCodigo()+" Cod P:"+funcionario.getPessoa().getCodigo());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		if(funcionario == null) {
			System.out.println("Funcionario não existente");
		}else {
			funcionarioDAO.excluir(funcionario);
			System.out.println("Funcionario excluído com sucesso!");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		if(funcionario == null) {
			System.out.println("Cliente não existe");
		}else if(pessoa == null){
			System.out.println("Pessoa não existe");
		}else {
			pessoa.setNome("Alterado Teste");
			funcionario.setCarteiraTrabalho("4645445");
			funcionario.setPessoa(pessoa);
			funcionarioDAO.editar(funcionario);
			
			System.out.println("Pessoa"+funcionario.getPessoa().getNome()+" - "+funcionario.getCarteiraTrabalho());
		}
	
}
}
