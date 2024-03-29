package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.ClienteDAO;
import br.com.eduardo.drogaria.dao.EmailDAO;
import br.com.eduardo.drogaria.dao.FuncionarioDAO;
import br.com.eduardo.drogaria.dao.ProdutoDAO;
import br.com.eduardo.drogaria.dao.VendaDAO;
import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.CreateEmail;
import br.com.eduardo.drogaria.domain.Funcionario;
import br.com.eduardo.drogaria.domain.ItemVenda;
import br.com.eduardo.drogaria.domain.Produto;
import br.com.eduardo.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	String mensagem2;
	
	private Venda venda;

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void novo() {
		try {
			ValidaBean validaBean = new ValidaBean();
			validaBean.verifica();
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) { // pega o produto da linha corrente
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			// Quando se soma um tipo Short, o JAVA o transforma em int, precisando assim
			// que
			// o mesmo seja convertido novamente em um Short
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensVenda.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			venda.setHorario(new Date());

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado("pessoa.nome");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado("pessoa.nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um item para venda");
				return;
			}
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);

			String mensagem;
			
			for(ItemVenda itemVenda : itensVenda) {
				mensagem2 += "\n" + itemVenda.getProduto().getDescricao() + " - R$" + itemVenda.getProduto().getPreco() + " | QTD:" + itemVenda.getQuantidade();
			}
			
			String mensagem3 =  "\n Atenciosamente, \n           Drogaria Multifarma - Programação Web com Java";
			String mensagem1 = "Prezado(a) " + venda.getCliente().getPessoa().getNome()
					+ ",\n foi realizada uma compra em seu nome na Drogaria MultiFarma, no valor de R$"
					+ venda.getPrecoTotal() + " para o CPF " + venda.getCliente().getPessoa().getCpf() + "\n Vendedor: "
							+ venda.getFuncionario().getPessoa().getNome() + "\n Produtos: ";
			
			mensagem = mensagem1 + "" + mensagem2 + "" + mensagem3;
			
			String destinatario = venda.getCliente().getPessoa().getEmail();
			String assunto = "Comprovante de compra";

			CreateEmail createEmail = new CreateEmail();
			createEmail.email(destinatario, assunto, mensagem);
			novo();

			Messages.addGlobalInfo("Venda realizada com sucesso, comprovante enviado no email!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
		}
	}
}