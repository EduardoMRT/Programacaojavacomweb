package br.com.eduardo.drogaria.bean;

import java.util.List;

import org.junit.Test;

import br.com.eduardo.drogaria.dao.ClienteDAO;
import br.com.eduardo.drogaria.domain.Cliente;

public class ClienteBeanTest {
	@Test
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
		
		for(Cliente cliente : resultado) {
			System.out.println("\n"+cliente.getPessoa().getNome()+" - "+cliente.getDataCadastro());
		}
	}
}
