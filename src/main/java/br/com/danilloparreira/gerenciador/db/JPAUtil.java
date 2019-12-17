package br.com.danilloparreira.gerenciador.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final String PERSISTENCE_UNIT_NAME = "gerenciador";
	private static EntityManagerFactory factory = null;

	static {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		} catch (Throwable ex) {
			System.out.println("Não conseguiu criar a instância da EntityManagerFactory: " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (!factory.isOpen())
			throw new RuntimeException("EntityManagerFactory não foi instânciado!");
		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
