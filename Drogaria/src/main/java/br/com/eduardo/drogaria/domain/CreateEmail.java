package br.com.eduardo.drogaria.domain;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class CreateEmail{
	
	public void email() {
		String meuEmail = "desenvolvimentostestes@gmail.com";
		String minhaSenha = "dgvtcyiyjwiwsykz";
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
		email.setSSLOnConnect(true);
		
		try {
			
			email.setFrom(meuEmail);
			email.setSubject("Teste de Envio de Emails");
			email.setMsg("Funcionou!");
			email.addTo(meuEmail);
			email.send();
			System.out.println("Email enviado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}