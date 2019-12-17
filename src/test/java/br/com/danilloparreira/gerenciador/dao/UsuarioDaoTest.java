package br.com.danilloparreira.gerenciador.dao;

import static org.junit.Assert.assertNotNull;

import javax.persistence.PersistenceException;

import org.junit.Test;

import br.com.danilloparreira.gerenciador.model.Usuario;

public class UsuarioDaoTest {

	@Test
	public void deveSalvarNovoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@email.com");
		usuario.setLogin("test");
		usuario.setSenha("test");

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		Usuario usuarioSalvo = usuarioDao.merge(usuario);

		assertNotNull(usuarioSalvo.getId());
	}

	@Test(expected = PersistenceException.class)
	public void naoDeveSalvarUsuarioSemEmail() {
		Usuario usuario = new Usuario();
		usuario.setLogin("test");
		usuario.setSenha("test");

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuarioDao.merge(usuario);

	}

	@Test(expected = PersistenceException.class)
	public void naoDeveSalvarUsuarioSemLogin() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@email.com");
		usuario.setSenha("test");

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuarioDao.merge(usuario);
	}

	@Test(expected = PersistenceException.class)
	public void naoDeveSalvarUsuarioSemSenha() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@email.com");
		usuario.setLogin("test");

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuarioDao.merge(usuario);
	}

}
