package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.eduardo.drogaria.dao.HistoricoDAO;
import br.com.eduardo.drogaria.dao.ProdutoDAO;
import br.com.eduardo.drogaria.domain.Historico;
import br.com.eduardo.drogaria.domain.Produto;
import br.com.eduardo.drogaria.util.HibernateUtil;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@Getter
@Setter
public class HistoricoBean implements Serializable {
	private Produto produto;
	private Boolean exibePainelDados;
	private Boolean exibeTabelaDados;
	private Historico historico;
	private List<Produto> produtos;
	private List<Historico> historicos;

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibePainelDados = false;
		exibeTabelaDados = false;
	}

	public void mostrar() {
		if (exibeTabelaDados != true) {
			exibeTabelaDados = true;
			listar();
		} else {
			exibeTabelaDados = false;
		}
	}

	
	public void listar() {
		try {

			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicos = historicoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
			erro.printStackTrace();
		}

	}

	public void buscar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());

			if (resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("O produto não existe");

			} else {
				exibePainelDados = true;
				produto = resultado;

			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);

			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);

			Messages.addGlobalInfo("Histórico salvo com sucesso");
			exibePainelDados = false;
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			erro.printStackTrace();
		}
	}

	public void excluir() {
		try {
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.excluir(historico);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir o histórico");
			erro.printStackTrace();
		}
	}
	
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formularioListar:tabela");
			
			String caminho = Faces.getRealPath("/resources/relatorios/historico.jasper");
			Map<String, Object> filtros = tabela.getFilters();
			Map<String, Object> parametros = new HashMap<>();
			
			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar imprimir o relatório");
			erro.printStackTrace();
		}
	}
}