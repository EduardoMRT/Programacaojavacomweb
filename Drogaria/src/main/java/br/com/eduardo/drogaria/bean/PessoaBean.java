package br.com.eduardo.drogaria.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.omnifaces.util.Messages;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.eduardo.drogaria.dao.CidadeDAO;
import br.com.eduardo.drogaria.dao.EstadoDAO;
import br.com.eduardo.drogaria.dao.PessoaDAO;
import br.com.eduardo.drogaria.domain.Cidade;
import br.com.eduardo.drogaria.domain.Estado;
import br.com.eduardo.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;

	private Cidade cidade;
	private List<Estado> estados;
	private List<Cidade> cidades;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@PostConstruct
	public void listar() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			cidades = new ArrayList<Cidade>(); // combos dependentes
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			estado = pessoa.getCidade().getEstado();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar editar uma pessoa");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();

			// Pega o cep e retira o traço
			String cep = pessoa.getCep();
			cep = cep.replace("-", "");

			// Faz a requisição
			String urlstr = "https://viacep.com.br/ws/" + cep + "/xml/";
			URL url = new URL(urlstr);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			// Pega os valores
			InputStream inputStream = connection.getInputStream();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(inputStream);
			Element root = document.getDocumentElement();

			String logradouro = root.getElementsByTagName("logradouro").item(0).getTextContent();
			String bairro = root.getElementsByTagName("bairro").item(0).getTextContent();
			String uf = root.getElementsByTagName("uf").item(0).getTextContent();
			String localidade = root.getElementsByTagName("localidade").item(0).getTextContent();

			Estado estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			estado = estadoDAO.buscarPorSigla(uf);

			Cidade cidade = new Cidade();
			cidade.setNome(localidade);
			cidade.setEstado(estado);
			pessoa.setCidade(cidade);
			pessoa.setRua(logradouro);
			pessoa.setBairro(bairro);

			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar("nome");
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			Messages.addGlobalInfo("Pessoa: " + pessoa.getNome() + " excluída com sucesso!");
			novo();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a pessoa");
			erro.printStackTrace();
		}
	}

//	public void popular() {
//
//		try {
//			if (estado != null) {
//				CidadeDAO cidadeDAO = new CidadeDAO();
//				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
//
//			} else {
//				cidades = new ArrayList<Cidade>();
//			}
//		} catch (RuntimeException erro) {
//			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
//			erro.printStackTrace();
//		}
//
//	}
}