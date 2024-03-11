package br.com.eduardo.drogaria.verificador;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;

public class VerificaCPF {
	String cpfStr;
	int cpfTeste;
	long cpfLong;
	// cpf tem 11 num

	public boolean verificar(String cpf) {
		int invalido = 0;
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(".", "");
		cpfLong = Long.parseLong(cpf);

		if (cpf.length() < 14) {
			invalido = 1;
		}

		for (int i = 0; i <= 9; i++) {
			int x = 0;
			if ((i == i) && (x <= 9)) {
				cpfStr = "";
				for (int y = 1; y <= 11; y++) {
					cpfStr = cpfStr + "" + i;
				}
				if (!cpfStr.contentEquals(cpf)) {
					invalido = 1;
				}
			}
			x++;
		}

		CheckDigit cpfcheckDigit = new CheckDigit() {

			@Override
			public boolean isValid(String code) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String calculate(String code) throws CheckDigitException {
				// TODO Auto-generated method stub
				return null;
			}
		};

		if (cpfcheckDigit.isValid(cpf) == false) {
			invalido = 1;
		}

		boolean valida = invalido == 1 ? false : true;
		return valida;
	}

}
