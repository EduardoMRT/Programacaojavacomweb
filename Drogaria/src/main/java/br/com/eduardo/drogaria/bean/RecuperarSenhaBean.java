package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
import java.lang.Math;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private int digitoSenha;

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
					+ "\nAtenciosamente, \n							Drogaria MultiFarma - Programação Web Com Java";

			createEmail.email(email, assunto, mensagem);

			usuario.setSenhaTemporaria(senha);
			Messages.addGlobalInfo("Uma nova senha foi enviada ao seu email");
			usuarioDAO.merge(usuario);
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
}
