package br.com.danilloparreira.gerenciador.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.persistence.PersistenceException;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.danilloparreira.gerenciador.model.Perfil;
import br.com.danilloparreira.gerenciador.model.Usuario;

public class UsuarioDaoTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveSalvarNovoUsuario() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setEmail("usuarionovo@email.com");
		usuario.setLogin("usuarionovo");
		usuario.setSenha("senha");

		Usuario usuarioSalvo = usuarioDao.merge(usuario);
		assertNotNull(usuarioSalvo.getId());
	}

	@Test
	public void deveAtualizarUsuario() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setEmail("usuario@email.com");
		usuario.setLogin("Usuario");
		usuario.setSenha("senha");

		Usuario usuarioSalvo = usuarioDao.merge(usuario);
		usuarioSalvo.setLogin("UsuarioEditado");
		Usuario usuarioEditado = usuarioDao.merge(usuarioSalvo);
		assertThat(usuarioEditado.getLogin(), CoreMatchers.not("usuario"));
	}

	@Test
	public void deveSalvarNovoUsuarioComPerfil() {
		Perfil perfil = new Perfil();
		perfil.setDescricao("Sem Permissão");
		PerfilDao perfilDao = new PerfilDaoImpl();
		Perfil perfilSalvo = perfilDao.merge(perfil);
		assertNotNull(perfilSalvo.getId());

		Usuario usuario = new Usuario();
		usuario.setEmail("usuariocomperfil@email.com");
		usuario.setLogin("usuariocomperfil");
		usuario.setSenha("senha");
		usuario.setPerfil(perfilSalvo);

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		Usuario usuarioSalvo = usuarioDao.merge(usuario);
		assertThat(usuarioSalvo.getPerfil().getDescricao(), is("Sem Permissão"));
	}

	@Test
	public void deveSalvarEmailEloginEmCaixaBaixa() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setEmail("CaixaBaixa@email.com");
		usuario.setLogin("CaixaBaixa");
		usuario.setSenha("senha");

		Usuario usuarioSalvo = usuarioDao.merge(usuario);

		assertThat(usuarioSalvo.getEmail(), is("caixabaixa@email.com"));
		assertThat(usuarioSalvo.getLogin(), is("caixabaixa"));
	}

	@Test
	public void naoDeveSalvarNovoUsuarioComEmailExistente() {
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario1.setEmail("emailexistente@email.com");
		usuario1.setLogin("emailexistente2");
		usuario1.setSenha("senha");

		usuario2.setEmail("emailexistente@email.com");
		usuario2.setLogin("emailexistente2");
		usuario2.setSenha("senha");

		Usuario usuarioSalvo = usuarioDao.merge(usuario1);

		assertNotNull(usuarioSalvo.getId());
		exception.expect(PersistenceException.class);
		usuarioDao.merge(usuario2);

	}

	@Test
	public void naoDeveSalvarNovoUsuarioComLoginExistente() {
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario1.setEmail("loginexistente1@email.com");
		usuario1.setLogin("loginexistente");
		usuario1.setSenha("senha");

		usuario2.setEmail("loginexistente2@email.com");
		usuario2.setLogin("loginexistente");
		usuario2.setSenha("senha");

		Usuario usuarioSalvo = usuarioDao.merge(usuario1);

		assertNotNull(usuarioSalvo.getId());
		exception.expect(PersistenceException.class);
		usuarioDao.merge(usuario2);

	}

	@Test
	public void naoDeveSalvarUsuarioSemEmail() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setLogin("test");
		usuario.setSenha("test");

		exception.expect(Exception.class);
		usuarioDao.merge(usuario);

	}

	@Test
	public void naoDeveSalvarUsuarioSemLogin() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setEmail("teste@email.com");
		usuario.setSenha("test");

		exception.expect(Exception.class);
		usuarioDao.merge(usuario);
	}

	@Test
	public void naoDeveSalvarUsuarioSemSenha() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();

		usuario.setEmail("teste@email.com");
		usuario.setLogin("test");

		exception.expect(Exception.class);
		usuarioDao.merge(usuario);
	}

}
