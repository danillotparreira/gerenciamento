package br.com.danilloparreira.gerenciador.dao.generic;

import java.util.List;

public interface GenericDao<T> {

	public void save(T entity);

	public T merge(T entity);

	public void delete(T entity);

	public T findById(Long id);

	public void deleteById(T entity);

	public List<T> findAll();

	public int size();

}
