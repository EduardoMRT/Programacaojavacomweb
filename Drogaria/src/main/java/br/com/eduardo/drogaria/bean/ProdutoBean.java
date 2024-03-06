package br.com.eduardo.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.eduardo.drogaria.dao.FabricanteDAO;
import br.com.eduardo.drogaria.dao.ProdutoDAO;
import br.com.eduardo.drogaria.domain.Fabricante;
import br.com.eduardo.drogaria.domain.Produto;
import br.com.eduardo.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("C:/eclipsejdk/Workspace/Uploads/" + produto.getCodigo() + ".png");
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(produto.getCaminho() == null) {
				Messages.addGlobalError("O campo foto é obrigatório!");
				return;
			}
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);
			
			java.nio.file.Path origem =  Paths.get(produto.getCaminho());
			java.nio.file.Path destino =  Paths.get("C:/eclipsejdk/Workspace/Uploads/" + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			java.nio.file.Path arquivo = Paths.get("C:/eclipsejdk/Workspace/Uploads/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);
			
			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		}
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			// pam 1 - nome do arquivo temp
			// pam 2 - extensao - .tmp
			// createTempFile - retorna tipo Path - guarda a referencia do arquivo
			java.nio.file.Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
			
			Messages.addGlobalInfo("Upload realizado com sucesso!");
		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload do arquivo");
			erro.printStackTrace();
		}
	}
	
	public void imprimir() {
		try {								 //idDoFormulario:idComponente
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			String proDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante.descricao");
			
			String caminho = Faces.getRealPath("/resources/relatorios/produtos.jasper");
			//C:\Eclipse\WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Drogaria\resources\relatorios
			Map<String, Object> parametros = new HashMap<>();
			
			if(proDescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			}else {
				parametros.put("PRODUTO_DESCRICAO", "%" + proDescricao + "%");
			}
			
			if(fabDescricao == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			}else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + fabDescricao + "%");
			}
			
			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
}