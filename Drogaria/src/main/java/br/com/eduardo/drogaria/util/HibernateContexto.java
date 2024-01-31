package br.com.eduardo.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//tem um método para quando o tomcat é ligado, e para quando ele é desligado
public class HibernateContexto implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().openSession();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

}
