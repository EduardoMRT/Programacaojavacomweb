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
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.codec.digest.DigestUtils;

//Lombok
@SuppressWarnings("serial")
@Getter
@Setter

//---------
@ManagedBean
@ViewScoped
public class EntrarBean implements Serializable {
	private Usuario usuario;
	private Pessoa pessoa;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private String confirmaSenha;
	private int contador;
	private Boolean validado = false;

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

	public void entrar(ActionEvent evento) {

		
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
					if (senhaCripto.equals(usuarioTeste.getSenha())) {
						usuario.setSenha(senhaCripto);
						validado = true;
						
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
					// Terminar bloqueio
				}

			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar entrar na sua conta");
				erro.printStackTrace();
			}

		}

	}
}
