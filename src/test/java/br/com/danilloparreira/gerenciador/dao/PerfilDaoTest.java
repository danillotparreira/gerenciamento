package br.com.danilloparreira.gerenciador.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.danilloparreira.gerenciador.model.CadastroAcao;
import br.com.danilloparreira.gerenciador.model.Perfil;
import br.com.danilloparreira.gerenciador.model.RelatorioAcao;
import br.com.danilloparreira.gerenciador.model.enuns.EnumCadastros;
import br.com.danilloparreira.gerenciador.model.enuns.EnumRelatorios;

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

	@Test
	public void deveBuscarPerfilEmCascata() {
		PerfilDao perfilDao = new PerfilDaoImpl();
		List<Perfil> lista = perfilDao.findAll();
		for (Perfil perfil : lista) {
			System.out.println("Perfil :" + perfil.getDescricao());
			System.out.println("|=============|");
			System.out.println("| Cadastros   |");
			System.out.println("|=============|");
			for (CadastroAcao acao : perfil.getCadastroAcoes()) {
				System.out.println("=============");
				System.out.println(acao.getEnumCadastros().getDescricao());
				System.out.println("Ler: " + acao.getListar());
				System.out.println("Criar: " + acao.getCriar());
				System.out.println("Editar: " + acao.getEditar());
				System.out.println("Remover: " + acao.getRemover());
			}
			System.out.println("|=============|");
			System.out.println("| Relatórios  |");
			System.out.println("|=============|");
			for (RelatorioAcao acao : perfil.getRelatorioAcoes()) {
				System.out.println(acao.getEnumRelatorios().getDescricao());
				System.out.println("Visualizar: " + acao.getVisualizar());
			}
			System.out.println("------------------");
		}
	}
}
