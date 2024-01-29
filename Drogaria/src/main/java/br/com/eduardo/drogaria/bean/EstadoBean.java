package br.com.eduardo.drogaria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class EstadoBean {
	public void salvar() {
		String texto = "Programação Web com Java";
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto); //tipo do erro, msg resumida e msg detalhada
	
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, mensagem); //Primeiro parametro sempre é nulo, e o segundo é a mensagem
	}
}

