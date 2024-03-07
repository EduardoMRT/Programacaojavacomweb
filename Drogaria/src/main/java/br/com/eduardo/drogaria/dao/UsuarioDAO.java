package br.com.eduardo.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.eduardo.drogaria.domain.Email;
import br.com.eduardo.drogaria.domain.Usuario;
import br.com.eduardo.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.createAlias("pessoa", "p");

			consulta.add(Restrictions.eq("p.cpf", cpf));
			consulta.add(Restrictions.eq("senha", senha));

			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}
	}

	public Usuario buscarPorCPF(String cpf) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.cpf", cpf));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}