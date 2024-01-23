package br.com.eduardo.drogaria.main;

import org.hibernate.Session;

import br.com.eduardo.drogaria.util.HibernateUtil;

public class HibernateUtilTest {
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		System.out.println("Sessão aberta");

		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
