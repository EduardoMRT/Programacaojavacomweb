package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.dao.ProdutoDAO;
import br.com.eduardo.drogaria.domain.Fabricante;
import br.com.eduardo.drogaria.domain.Produto;
import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Getter
@Setter
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable{
	private Produto produto;
	private Long codigoProduto;
	
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	private FabricanteDAO fabricanteDAO;
	private ProdutoDAO produtoDAO;
	
	@PostConstruct
	public void inicar() {
		fabricanteDAO = new FabricanteDAO();
		produtoDAO = new ProdutoDAO();
	}
	
	public void listar() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			produtos = produtoDAO.listar("descricao");	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void carregarEdicao() {
		try {
			produto = produtoDAO.buscar(codigoProduto);
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um merro ao tentar carregar os dados para edição");
			erro.printStackTrace();
		}
	}
}
