package br.com.danilloparreira.gerenciador.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.danilloparreira.gerenciador.dao.UsuarioDao;
import br.com.danilloparreira.gerenciador.dao.UsuarioDaoImpl;
import br.com.danilloparreira.gerenciador.model.CadastroAcao;
import br.com.danilloparreira.gerenciador.model.RelatorioAcao;
import br.com.danilloparreira.gerenciador.model.Usuario;

@SessionScoped
@ManagedBean(name = "loginbean")
public class LoginBean extends AbstractBean {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	private Usuario usuario;
	private String login;
	private String senha;
	private boolean loggedIn = false;

	public LoginBean() {
		super("Acesso ao Sistema");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public CadastroAcao getCadastroAcao(String enumCadastros) {
		for (CadastroAcao cadastroAcao : usuario.getPerfil().getCadastroAcoes()) {
			if (cadastroAcao.getEnumCadastros().getDescricao().equalsIgnoreCase(enumCadastros)) {
				return cadastroAcao;
			}
		}
		return null;
	}

	public RelatorioAcao getRelatorioAcao(String enumRelatorio) {
		for (RelatorioAcao relatorioAcao : usuario.getPerfil().getRelatorioAcoes()) {
			if (relatorioAcao.getEnumRelatorios().getDescricao().equalsIgnoreCase(enumRelatorio)) {
				return relatorioAcao;
			}
		}
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String doLogin() {
		Usuario userFound = usuarioDao.isUsuarioReadyToLogin(login, senha);
		if (userFound == null) {
			msgError("Falha ao logar", "Login ou Senha errados, tente novamente!");
			FacesContext.getCurrentInstance().validationFailed();
			return "/login.xhtml?faces-redirect=true";
		} else {
			this.usuario = userFound;
			this.loggedIn = true;
			return "/index.xhtml?faces-redirect=true";
		}
	}

	public String doLogout() {
		this.usuario = null;
		msg("Logout realizado com sucesso !");
		this.loggedIn = false;
		return "/login.xhtml?faces-redirect=true";
	}
}
