package br.com.eduardo.drogaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.eduardo.drogaria.dao.UsuarioDAO;
import br.com.eduardo.drogaria.domain.Pessoa;
import br.com.eduardo.drogaria.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;
	private String senhaCripto;
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			senhaCripto = usuario.getSenha();
			senhaCripto = DigestUtils.sha256Hex(senhaCripto);
			
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), senhaCripto);

			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}

			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
}