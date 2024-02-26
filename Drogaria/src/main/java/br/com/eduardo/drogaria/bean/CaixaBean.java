package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.CaixaDAO;
import br.com.eduardo.drogaria.domain.Caixa;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@Getter
@Setter
public class CaixaBean implements Serializable {
	private Caixa caixa;
	private List<Caixa> caixas;
//	PRIVATE CAIXA CAIXAVERIFICA;

	@PostConstruct
	public void listar() {
		try {
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
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar abrir um caixa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CaixaDAO caixaDAO = new CaixaDAO();
			caixas = caixaDAO.listar();
			
			Messages.addGlobalInfo("Caixa aberto com sucesso");
			caixaDAO.merge(caixa);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar um caixa");
			erro.printStackTrace();
		}
	}
}
