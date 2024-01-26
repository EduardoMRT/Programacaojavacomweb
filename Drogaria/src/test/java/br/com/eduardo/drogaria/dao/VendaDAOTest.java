package br.com.eduardo.drogaria.dao;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.Funcionario;
import br.com.eduardo.drogaria.domain.Pessoa;
//Cliente e funcionario
import br.com.eduardo.drogaria.domain.Venda;

public class VendaDAOTest {
	@Test
	public void salvar() throws ParseException {
		//DAO VENDA
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		
		//DAO FUNCIONARIO
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		//DAO CLIENTE
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente Cliente = clienteDAO.buscar(1L);
		
		venda.setHorario(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse("26/01/2024 10:38"));
		venda.setPrecoTotal(new BigDecimal("17.99"));
		venda.setCliente(Cliente);
		venda.setFuncionario(funcionario);
		vendaDAO.salvar(venda);

		System.out.println("Venda salva com sucesso!: "+venda.getCodigo()+" - Cliente: "+venda.getCliente().getPessoa().getNome());
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
