package br.com.eduardo.drogaria.verificador;

public class VerificaCPF {
	String cpfStr;
	int cpfTeste;
	long cpfInt;
	// cpf tem 11 num

	public boolean verificar(String cpf) {
		int invalido = 0;
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(".", "");
		cpfInt = Long.parseLong(cpf);

		for (int i = 0; i <= 9; i++) {
			int x = 0;
			if ((i == i) && (x <= 9)) {
				cpfStr = "";
				for (int y = 1; y <= 11; y++) {
					cpfStr = cpfStr + "" + i;
				}
				if(!cpfStr.contentEquals(cpf)) {
					invalido = 1;
				}
			}
			x++;
		}
		boolean valida = invalido == 1 ? false : true;
		return valida;
	}

}
