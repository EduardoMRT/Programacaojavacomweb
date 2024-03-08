package br.com.eduardo.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.lang.Math;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.UsuarioDAO;
import br.com.eduardo.drogaria.domain.CreateEmail;
import br.com.eduardo.drogaria.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@Getter
@Setter
public class RecuperarSenhaBean implements Serializable {
	private String cpf;

	private String senhaTemp;
	private String codigo;
	private String novaSenha;
	private int digitoSenha;
	private Boolean visivel1;
	private Boolean visivel2;
	private Boolean visivel3;

	@PostConstruct
	public void inicio() {
		visivel1 = true;
		visivel2 = false;
		visivel3 = false;
	}

	public void recuperar() {

		try {
			String mensagem;
			String email;
			String assunto;

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
			CreateEmail createEmail = new CreateEmail();
			String senha = senhaTemporaria();
			email = usuario.getPessoa().getEmail();
			assunto = "Recuperação de Senha";
			mensagem = "Prezado(a)" + usuario.getPessoa().getNome()
					+ ", você solicitou uma alteração na sua senha. \nSua senha para redefinição é:" + senha
					+ "\nCaso você não tenha solicitado a alteração, ainda é possível entrar com a sua senha. \nAtenciosamente, \n							Drogaria MultiFarma - Programação Web Com Java";

			createEmail.email(email, assunto, mensagem);

			usuario.setSenhaTemporaria(senha);
			Messages.addGlobalInfo("Uma nova senha foi enviada ao seu email");
			usuarioDAO.merge(usuario);
			visivel1 = false;
			visivel2 = true;
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar recuperar a sua conta");
			e.printStackTrace();
		}
	}

	public String senhaTemporaria() {
		senhaTemp = null;
		for (int i = 0; i <= 7; i++) {
			Random random = new Random();
			digitoSenha = random.nextInt(10);
			if (senhaTemp == null) {
				senhaTemp = "" + digitoSenha;
			} else {
				senhaTemp += digitoSenha;
			}
		}
		return senhaTemp;
	}

	public void verificaCodigo() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
		System.out.println(codigo);
		if (usuario.getSenhaTemporaria().contentEquals(codigo)) {
			Messages.addGlobalInfo("Código confere!");
			visivel2 = false;
			visivel3 = true;
		}
		else {
			Messages.addGlobalWarn("Código de verificação não confere!");
		}
	}

	public void redefinir() {
		try {
			if ((novaSenha.length() >= 6) && (novaSenha.length() <= 8)) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
				novaSenha = DigestUtils.sha256Hex(novaSenha);
				usuario.setSenha(novaSenha);
				usuario.setSenhaTemporaria(null);
				usuarioDAO.merge(usuario);
				Messages.addGlobalInfo("Senha redefinida com sucesso!");
				visivel1 = true;
				visivel2 = false;
				Faces.redirect("./pages/principal.xhtml");

			} else {
				Messages.addGlobalWarn("Sua nova senha deve ter 8 caracteres!");
			}
		} catch (IOException | RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar redefinir a senha!");
			erro.printStackTrace();
		}

	}
}
