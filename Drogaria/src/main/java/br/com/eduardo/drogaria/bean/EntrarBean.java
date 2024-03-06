package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.PessoaDAO;
import br.com.eduardo.drogaria.dao.UsuarioDAO;
import br.com.eduardo.drogaria.domain.Pessoa;
import br.com.eduardo.drogaria.domain.Usuario;
import br.com.eduardo.drogaria.util.HibernateUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

//Lombok
@SuppressWarnings("serial")
@Getter
@Setter

//---------
@ManagedBean
@ViewScoped
public class EntrarBean implements Serializable{
	private Usuario usuario;
	private Pessoa pessoa;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private String confirmaSenha;
	private String cpf;
	private int contador;
	private Boolean validado = false;
	static Boolean validadoFinal = false;

	@PostConstruct
	public void listar() {
		try {
			usuario = new Usuario();
			usuarios = new ArrayList<Usuario>();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}

	}

	public Boolean entrar(ActionEvent evento) {

		
		usuarios = new ArrayList<Usuario>();

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar();

		if (!confirmaSenha.equals(usuario.getSenha())) {
			System.out.println(usuario.getSenha());
			System.out.println(confirmaSenha);
			Messages.addGlobalError("A senha e a senha confirmada não confere");
		} else {
			String senhaCripto = DigestUtils.sha256Hex(usuario.getSenha());
			
			try {
				if (usuarios.isEmpty()) {
					System.out.println("A lista de usuários está vazia.");
				}

				for (Usuario usuarioTeste : usuarios) {
					if ((senhaCripto.equals(usuarioTeste.getSenha())) && (cpf.equals(usuarioTeste.getPessoa().getCpf()))) {
						usuario.setSenha(senhaCripto);
						validado = true;
						validadoFinal = validado;
					}
				}
				if (validado != true) {
					Messages.addGlobalWarn("Senha incorreta!");
					contador++;
				}else {
					Messages.addGlobalInfo("Você entrou na sua conta com sucesso!");
				}
				if (contador >= 5) {
					Messages.addGlobalWarn("Para questões de segurança, o usuário foi bloqueado");
					Usuario usuarioBloqueado = usuarioDAO.buscar(usuario.getCodigo());
					usuarioBloqueado.setAtivo(false);
				}

			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar entrar na sua conta");
				erro.printStackTrace();
			}

		}
		return validadoFinal;
	}
	
	public Boolean sair() {
		validadoFinal = false;
		autentica();
		return validadoFinal;
	}
	
	public Boolean autentica() {
		return validadoFinal;
	}
}
