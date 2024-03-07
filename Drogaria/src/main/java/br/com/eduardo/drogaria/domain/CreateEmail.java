package br.com.eduardo.drogaria.domain;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import br.com.eduardo.drogaria.dao.EmailDAO;

public class CreateEmail {

	public void email(String destinatario, String assunto, String mensagem) {

		try {
			EmailDAO emailDAO = new EmailDAO();
			Email email2 = new Email();
			email2 = emailDAO.buscarPorCodigo(1);
			
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator(email2.getEmail(), email2.getSenha()));
			email.setSSLOnConnect(true);
			email.setFrom(email2.getEmail());
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.addTo(destinatario);
			email.send();
			System.out.println("Email enviado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}