package br.com.eduardo.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.eduardo.drogaria.domain.Email;
import br.com.eduardo.drogaria.util.HibernateUtil;

public class EmailDAO extends GenericDAO<Email> {
	public Email buscarPorCodigo(int idEmail) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Email.class);
			consulta.add(Restrictions.eq("idemail", idEmail));
			Email resultado = (Email) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}