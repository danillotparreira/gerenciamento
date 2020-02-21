package br.com.danilloparreira.gerenciador.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.danilloparreira.gerenciador.dao.PerfilDaoImpl;
import br.com.danilloparreira.gerenciador.dao.UsuarioDao;
import br.com.danilloparreira.gerenciador.dao.UsuarioDaoImpl;
import br.com.danilloparreira.gerenciador.model.Perfil;
import br.com.danilloparreira.gerenciador.model.Usuario;
import br.com.danilloparreira.gerenciador.utils.UtilSecurity;

@ManagedBean(name = "usuariobean")
@ViewScoped
public class UsuarioBean extends AbstractBean implements GenericBean {

	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private List<Usuario> usuarios;
	private UsuarioDao usuarioDao;
	private List<Perfil> perfis = new PerfilDaoImpl().findAll();

	public UsuarioBean() {
		super("Usuario");
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<>();
		this.usuarioDao = new UsuarioDaoImpl();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		usuarios = usuarioDao.findAll();
		return usuarios;
	}

	@Override
	public void create() {
		usuario = new Usuario();
	}

	@Override
	public String save() {
		if(usuario.getSenha().length() <= 20) {
			String senhaMd5 = UtilSecurity.convertStringToMd5(usuario.getSenha());
			usuario.setSenha(senhaMd5);
		}
		try {
			usuario = usuarioDao.merge(usuario);
			if (usuario.getId() != null) {
				getUsuarios();
				msgSalvar();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possivel salvar, já existe usuario com este login ou email.", ""));
		}
		
		return "";
	}

	@Override
	public String delete() {
		usuarioDao.deleteById(usuario);
		usuario = new Usuario();
		msgDeletar();
		reset();
		return "";
	}
	public String edit() {
		this.usuario = usuarioDao.findById(usuarioSelecionado.getId());
		return "";
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis() {
		this.perfis = new PerfilDaoImpl().findAll();
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
