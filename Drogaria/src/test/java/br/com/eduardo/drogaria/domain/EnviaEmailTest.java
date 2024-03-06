package br.com.eduardo.drogaria.domain;

import org.junit.Test;

public class EnviaEmailTest {
	CreateEmail createEmail = new CreateEmail();
	@Test
	public void criar() {
		createEmail.sendEmail("desenvolvimentostestes@gmail.com", "Teste", "Teste");
	}
}
