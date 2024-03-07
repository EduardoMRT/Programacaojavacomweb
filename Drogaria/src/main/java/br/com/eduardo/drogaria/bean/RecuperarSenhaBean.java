package br.com.eduardo.drogaria.bean;

import java.io.Serializable;
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

	public void recuperar() {
		try {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
			CreateEmail createEmail = new CreateEmail();
			String senha = senhaTemporaria();
			createEmail.email(usuario.getPessoa().getEmail(), "Recuperação de Senha", "Prezado(a)"
					+ usuario.getPessoa().getNome() + ", sua senha para redefinição é:" + senha);
			
			usuario.setSenhaTemporaria(senha);
			Messages.addGlobalInfo("Uma nova senha foi enviada ao seu email");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar recuperar a sua conta");
			e.printStackTrace();
		}
	}

	public String senhaTemporaria() {
		senhaTemp = null;
		for (int i = 0; i <= 7; i++) {
			Random senhaTempAleatoria = new Random();
			senhaTemp += senhaTempAleatoria;
		}
		return senhaTemp;
	}
}
