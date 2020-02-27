package br.com.danilloparreira.gerenciador.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;

import br.com.danilloparreira.gerenciador.dao.PerfilDao;
import br.com.danilloparreira.gerenciador.dao.PerfilDaoImpl;
import br.com.danilloparreira.gerenciador.model.CadastroAcao;
import br.com.danilloparreira.gerenciador.model.Perfil;

@ManagedBean(name = "perfilbean")
@ViewScoped
public class PerfilBean extends AbstractBean implements GenericBean {

	private Perfil perfil;
	private Perfil perfilSelecionado;
	private List<Perfil> perfis;
	private PerfilDao perfilDao;

	public PerfilBean() {
		super("Perfil");
		this.perfil = new Perfil();
		this.perfilDao = new PerfilDaoImpl();
		this.perfis = perfilDao.findAll();
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setPerfis() {
		this.perfis = perfilDao.findAll();
	}

	public List<Perfil> getPerfis() {
		perfis = perfilDao.findAll();
		return perfis;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	@Override
	public void create() {
		this.perfil = new Perfil();
	}

	@Override
	public String save() {
		try {
			perfil = perfilDao.merge(perfil);
			for (CadastroAcao cadastroAcao : perfil.getCadastroAcoes()) {
				cadastroAcao.updateAlternarSelecao();
			}
			if (perfil != null) {
				getPerfis();
				msgSalvar();
			}
		} catch (Exception e) {
			msg("Já existe um cadastro com a descrição informada!");
		}
		return "";
	}

	@Override
	public String delete() {
		try {
			perfilDao.deleteById(perfil);
			msgDeletar();
			create();
			reset();

		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				msgError("Não foi possivel deletar pois existe usuário(s) cadastrado(s) para este perfil.",
						"Erro ao deletar");
			}
		}
		return "";
	}

	public String edit() {
		Perfil perfilTemp = perfilDao.findById(perfilSelecionado.getId());
		for (CadastroAcao cadastroAcao : perfilTemp.getCadastroAcoes()) {
			cadastroAcao.updateAlternarSelecao();
		}
		this.perfil = perfilTemp;
		return "";
	}

	public void triStateCheckboxEvent(String descricao) {
		for (CadastroAcao cadastroAcao : perfil.getCadastroAcoes()) {
			if (cadastroAcao.getEnumCadastros().getDescricao().equals(descricao)) {
				cadastroAcao.clickAlternarSelecao();
				return;
			}
		}
	}

}
