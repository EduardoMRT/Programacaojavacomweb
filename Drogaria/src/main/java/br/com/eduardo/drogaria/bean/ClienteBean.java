package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.ClienteDAO;
import br.com.eduardo.drogaria.dao.PessoaDAO;
import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private Cliente cliente;
	private List<Cliente> clientes;
	
	private List<Pessoa> pessoas;
	
	private Pessoa pessoa;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@PostConstruct
	public void listar(){
		ValidaBean validaBean = new ValidaBean();
		validaBean.verifica();
//		System.out.println(entrarBean.autentica() == false ? "Você precisa fazer login para acessar a página" : "Usuário autenticado");  
		try{
			novo();
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			//ClienteDAO clienteDAO = new ClienteDAO();
			cliente = new Cliente();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			
	}catch (RuntimeException erro) {
		Messages.addGlobalError("Não foi possível criar um novo cliente");
		erro.printStackTrace();
	}
}
	
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			
			cliente = new Cliente();
			clientes = clienteDAO.listar("dataCadastro");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Novo cliente salvo com sucesso");
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			ClienteDAO clienteDAO = new ClienteDAO();
			
			clienteDAO.excluir(cliente);
			Messages.addGlobalInfo("O cliente foi excluído com sucesso!");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o cliente");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
	}
}