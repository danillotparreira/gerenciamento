package br.com.danilloparreira.gerenciador.db;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JPAUtilTest {

	@Test
	public void deveCriarInstanciaDoEntityManager() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			assertNotNull(em);
		} catch (Throwable e) {
			assertThat(e.getMessage(), is("Erro ao conectar ao banco de dados confira os dados do persistence.xml"));
		}
	}

}
