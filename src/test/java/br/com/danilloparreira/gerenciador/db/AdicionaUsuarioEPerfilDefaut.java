package br.com.danilloparreira.gerenciador.db;

import org.junit.Test;

import br.com.danilloparreira.gerenciador.dao.PerfilDao;
import br.com.danilloparreira.gerenciador.dao.PerfilDaoImpl;
import br.com.danilloparreira.gerenciador.dao.UsuarioDao;
import br.com.danilloparreira.gerenciador.dao.UsuarioDaoImpl;
import br.com.danilloparreira.gerenciador.model.CadastroAcao;
import br.com.danilloparreira.gerenciador.model.Perfil;
import br.com.danilloparreira.gerenciador.model.RelatorioAcao;
import br.com.danilloparreira.gerenciador.model.Usuario;
import br.com.danilloparreira.gerenciador.utils.UtilSecurity;

public class AdicionaUsuarioEPerfilDefaut {

	@Test
	public void adicionaUsuarioAdministradorSuporteEPerfilSemPermissaoEAdministrador() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		PerfilDao perfilDao = new PerfilDaoImpl();

		Perfil perfil1 = new Perfil("Sem Permissão");
		perfilDao.merge(perfil1);
		Perfil perfil2 = new Perfil("Administrador");
		for (CadastroAcao cadastroAcao : perfil2.getCadastroAcoes()) {
			cadastroAcao.setCriar(true);
			cadastroAcao.setEditar(true);
			cadastroAcao.setListar(true);
			cadastroAcao.setRemover(true);
		}
		for (RelatorioAcao relatorioAcao : perfil2.getRelatorioAcoes()) {
			relatorioAcao.setVisualizar(true);
		}
		perfil2 = perfilDao.merge(perfil2);
		Perfil perfil3 = new Perfil("Suporte");
		for (CadastroAcao cadastroAcao : perfil3.getCadastroAcoes()) {
			cadastroAcao.setCriar(true);
			cadastroAcao.setEditar(true);
			cadastroAcao.setListar(true);
			cadastroAcao.setRemover(true);
		}
		for (RelatorioAcao relatorioAcao : perfil3.getRelatorioAcoes()) {
			relatorioAcao.setVisualizar(true);
		}
		perfil3 = perfilDao.merge(perfil3);
		Usuario administrador = new Usuario("admin@gmail.com", "Administrador", "admin",
				UtilSecurity.convertStringToMd5("admin"));
		Usuario suporte = new Usuario("suporte@gmail.com", "Suporte", "suporte",
				UtilSecurity.convertStringToMd5("suporte"));
		suporte.setPerfil(perfil3);
		administrador.setPerfil(perfil2);
		usuarioDao.merge(suporte);
		usuarioDao.merge(administrador);
	}

}
