package br.com.eduardo.drogaria.domain;

import org.junit.Test;

public class EnviaEmailTest {
	@Test
	public void enviarEmail() {
		CreateEmail createEmail = new CreateEmail();
		createEmail.email();
	}
}
