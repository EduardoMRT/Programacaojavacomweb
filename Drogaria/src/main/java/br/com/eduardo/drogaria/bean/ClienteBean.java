package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.ClienteDAO;
import br.com.eduardo.drogaria.dao.PessoaDAO;
import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private List<Cliente> clientes;
	private Cliente cliente;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar(){
		try{
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("dataCadastro");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			//ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			
	}catch (RuntimeException erro) {
		Messages.addGlobalError("Não foi possível criar um novo cliente");
		erro.printStackTrace();
	}
}
}