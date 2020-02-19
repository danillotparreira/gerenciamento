package br.com.danilloparreira.gerenciador.dao;

import static br.com.danilloparreira.gerenciador.db.JPAUtil.getEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.danilloparreira.gerenciador.dao.generic.GenericDaoImpl;
import br.com.danilloparreira.gerenciador.model.Usuario;
import br.com.danilloparreira.gerenciador.utils.UtilSecurity;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

	@Override
	public Usuario isUsuarioReadyToLogin(String login, String senha) {
		try {
			login = login.toLowerCase().trim();
//			logger.info("Verificando login do usuário " + login);
			EntityManager em = getEntityManager();
			TypedQuery<Usuario> query = em
					.createQuery("Select u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class)
					.setParameter("login", login.trim().toLowerCase())
					.setParameter("senha", UtilSecurity.convertStringToMd5(senha));

			List<Usuario> result = query.getResultList();
			if (result.size() == 1) {
				return (Usuario) result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Usuario> findAll() {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		List<Usuario> result = em.createQuery("SELECT u FROM Usuario u WHERE u.login <> 'suporte'", Usuario.class)
				.getResultList();
		entityTransaction.commit();
		em.close();
		return result;
	}

	public List<Usuario> findAll(Usuario usuarioLogado) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		List<Usuario> result = em.createQuery("SELECT u FROM Usuario u WHERE u.login <> 'suporte' and ", Usuario.class).setParameter("perfil", usuarioLogado.getPerfil().getId())
				.getResultList();
		entityTransaction.commit();
		em.close();
		return result;
	}

}
