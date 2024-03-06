package br.com.eduardo.drogaria.bean;

import java.io.IOException;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

public class ValidaBean {
	public void verifica() {
		try {
			EntrarBean entrarBean = new EntrarBean();
			if(entrarBean.autentica() == false) {
				Messages.addGlobalInfo("Você precisa estar logado para acessar essa página");
				Faces.redirect("./pages/entrar.xhtml");
			}
		} catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao verificar o usuário");
			erro.printStackTrace();
		}
	}
}
