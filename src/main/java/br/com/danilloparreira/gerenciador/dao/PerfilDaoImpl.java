package br.com.danilloparreira.gerenciador.dao;

import static br.com.danilloparreira.gerenciador.db.JPAUtil.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.danilloparreira.gerenciador.dao.generic.GenericDaoImpl;
import br.com.danilloparreira.gerenciador.model.Perfil;

public class PerfilDaoImpl extends GenericDaoImpl<Perfil> implements PerfilDao {

	@Override
	public List<Perfil> findAll() {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		List<Perfil> result = em.createQuery(
				"SELECT p FROM Perfil p WHERE p.descricao <> 'Suporte' and p.descricao <> 'Sem Permissão'",
				Perfil.class).getResultList();
		entityTransaction.commit();
		em.close();
		return result;
	}

}
