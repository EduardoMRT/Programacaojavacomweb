package br.com.eduardo.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.eduardo.drogaria.domain.Estado;
import br.com.eduardo.drogaria.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {
	public Estado buscarPorSigla(String uf) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.createAlias("cidade", "c");
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.c.cep", uf));
			Estado resultado = (Estado) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
