package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.PessoaDAO;
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

	public void entrar() {
		if (!confirmaSenha.equals(usuario.getSenha())) {
			System.out.println(usuario.getSenha());
			System.out.println(confirmaSenha);
			Messages.addGlobalError("A senha e a senha confirmada não confere");
		} else {
			String senhaCripto = DigestUtils.sha256Hex(usuario.getSenha());
			try {
				for (Usuario usuarioTeste : usuarios) {
					System.out.println("Usuario teste senha: "+usuarioTeste.getSenha());
					if (senhaCripto.equals(usuarioTeste.getSenha())) {
						usuario.setSenha(senhaCripto);
						validado = true;
						Messages.addGlobalInfo("Você entrou na sua conta com sucesso!");
					}
				}
				if (validado != true) {
					Messages.addGlobalWarn("Senha incorreta!");
					contador++;
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
