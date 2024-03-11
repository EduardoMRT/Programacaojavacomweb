package br.com.eduardo.drogaria.verificador;

import org.junit.Test;

public class VerificaRCPF {
	String cpfStr;

	@Test
	public void teste() {
		
		VerificaCPF verificaCPF = new VerificaCPF();
		System.out.println(verificaCPF.verificar("111.111.111-11") == false ? "CPF Inválido" : "CPF Válido");
		
//		for (int i = 0; i <= 9; i++) {
//			int x = 0;
//			if ((i == i) && (x <= 9)) {
//				cpfStr = "";
//				for (int y = 1; y <= 11; y++) {
//					cpfStr = cpfStr + "" + i;
//				}
//				System.out.println(cpfStr);
//			}
//			x++;
//		}
		
//		String cpf = "123.456.789-10";
//		cpf = cpf.replace(".", "");
//		cpf = cpf.replace("-", "");
//		
//		long cpf2 = Long.parseLong(cpf);
//		System.out.println(cpf2);

	}
}
