package br.com.danilloparreira.gerenciador.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.danilloparreira.gerenciador.model.EnumCadastros;
import br.com.danilloparreira.gerenciador.model.EnumRelatorios;
import br.com.danilloparreira.gerenciador.model.Perfil;

public class PerfilDaoTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void deveSalvarNovoPerfil() {
		Perfil perfil = new Perfil();
		perfil.setDescricao("Administrador");

		PerfilDao perfilDao = new PerfilDaoImpl();
		Perfil perfilSalvo = perfilDao.merge(perfil);
		assertNotNull(perfilSalvo.getId());
		assertThat(perfilSalvo.getCadastroAcoes().size(), is(EnumCadastros.values().length));
		assertThat(perfilSalvo.getRelatorioAcoes().size(), is(EnumRelatorios.values().length));
	}

	@Test
	public void naoDeveSalvarPerfilSemDescricao() {
		Perfil perfil = new Perfil();
		PerfilDao perfilDao = new PerfilDaoImpl();

		exception.expect(Exception.class);
		perfilDao.merge(perfil);
	}

	@Test
	public void naoDeveSalvarPerfilComDescricaoRepetida() {
		Perfil perfil1 = new Perfil();
		perfil1.setDescricao("descricaorepetida");
		Perfil perfil2 = new Perfil();
		perfil2.setDescricao("descricaorepetida");
		PerfilDao perfilDao = new PerfilDaoImpl();
		Perfil perfilSalvo = perfilDao.merge(perfil1);
		assertNotNull(perfilSalvo.getId());
		exception.expect(Exception.class);
		perfilDao.merge(perfil2);
	}
}
