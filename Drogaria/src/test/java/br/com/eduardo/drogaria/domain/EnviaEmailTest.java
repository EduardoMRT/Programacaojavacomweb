package br.com.eduardo.drogaria.domain;

import org.junit.Ignore;
import org.junit.Test;

public class EnviaEmailTest {
	@Ignore
	@Test
	public void enviarEmail() {
		CreateEmail createEmail = new CreateEmail();
		createEmail.email("eduardoteixeira.dev@gmail.com", "Teste de Emails para outros usuarios", "Testando emails para outros usuarios");
	}
}
