package br.com.danilloparreira.gerenciador.dao.generic;

import static br.com.danilloparreira.gerenciador.db.JPAUtil.getEntityManager;
import static br.com.danilloparreira.gerenciador.db.JPAUtil.getPrimaryKey;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GenericDaoImpl<T> implements GenericDao<T> {

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(entity);
		entityTransaction.commit();
		em.close();
	}

	public T merge(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		T result = em.merge(entity);
		entityTransaction.commit();
		em.close();
		return result;
	}

	public void delete(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(entity);
		entityTransaction.commit();
		em.close();
	}

	public T findById(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		T entity = em.find(clazz, id);
		em.close();
		return entity;

	}

	public void deleteById(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		Object id = getPrimaryKey(entity);
		em.createQuery("delete from " + entity.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		entityTransaction.commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		List<T> result = (List<T>) em.createQuery("from " + clazz.getCanonicalName()).getResultList();
		entityTransaction.commit();
		em.close();
		return result;
	}

	@Override
	public int size() {
		EntityManager em = getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		int result = em.createQuery("from entity :name").setParameter("name", clazz.getCanonicalName()).getMaxResults();
		entityTransaction.commit();
		em.close();
		return result;
	}

}