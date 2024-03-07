package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.CaixaDAO;
import br.com.eduardo.drogaria.dao.FuncionarioDAO;
import br.com.eduardo.drogaria.domain.Caixa;
import br.com.eduardo.drogaria.domain.Funcionario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@Getter
@Setter
public class CaixaBean implements Serializable {
	private Caixa caixa;
	private String nome;
	private Funcionario funcionario;
	private List<Caixa> caixas;
	private List<Funcionario> funcionarios;
//	PRIVATE CAIXA CAIXAVERIFICA;

	@PostConstruct
	public void listar() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			for (Funcionario teste : funcionarios) {
				System.out.println(teste.getPessoa().getNome());
			}

			CaixaDAO caixaDAO = new CaixaDAO();
			caixas = caixaDAO.listar();
			System.out.println(caixas);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar a listagem de caixa");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			caixa = new Caixa();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar abrir um caixa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CaixaDAO caixaDAO = new CaixaDAO();
			caixaDAO.merge(caixa);
			
			caixa = new Caixa();
			caixas = caixaDAO.listar();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			Messages.addGlobalInfo("Caixa aberto com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar um caixa");
			erro.printStackTrace();
		}
	}
}
