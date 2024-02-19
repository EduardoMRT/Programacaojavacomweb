package br.com.eduardo.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.eduardo.drogaria.domain.Cliente;
import br.com.eduardo.drogaria.domain.Funcionario;
import br.com.eduardo.drogaria.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente>{
	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			//nome do atributo dentro da classe, nome que você inventou 
			consulta.createAlias("pessoa","p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Cliente> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
