package br.com.danilloparreira.gerenciador.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.danilloparreira.gerenciador.dao.UsuarioDao;
import br.com.danilloparreira.gerenciador.dao.UsuarioDaoImpl;
import br.com.danilloparreira.gerenciador.model.CadastroAcao;
import br.com.danilloparreira.gerenciador.model.RelatorioAcao;
import br.com.danilloparreira.gerenciador.model.Usuario;
import br.com.danilloparreira.gerenciador.utils.UtilSecurity;

@SessionScoped
@ManagedBean(name = "loginbean")
public class LoginBean extends AbstractBean {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	private Usuario usuario;
	private String login;
	private String senha;
	private boolean loggedIn = false;
	private String nome;
	private String email;
	private String confirmacaoEmail;
	private String senhanova;
	private String confirmacaoSenha;

	public LoginBean() {
		super("Acesso ao Sistema");
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmacaoEmail() {
		return confirmacaoEmail;
	}

	public void setConfirmacaoEmail(String confirmacaoEmail) {
		this.confirmacaoEmail = confirmacaoEmail;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getSenhanova() {
		return senhanova;
	}

	public void setSenhanova(String senhanova) {
		this.senhanova = senhanova;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		Boolean changed = false;
		String msg = "";

		if (this.nome != null) {
			this.usuario.setNome(this.nome);
			changed = true;
		} else if (UtilSecurity.convertStringToMd5(this.senha).equals(this.usuario.getSenha())) {
			if (this.email != null && this.confirmacaoEmail != null && this.senha != null) {
				if (this.email.trim().equals(this.confirmacaoEmail.trim())) {
					this.usuario.setEmail(this.email);
					changed = true;
				} else {
					msg = "Os emails não são iguais.";
				}
			}
			if (this.senhanova != null && this.confirmacaoSenha != null) {
				if (this.senhanova.equals(this.confirmacaoSenha)) {
					this.usuario.setSenha(UtilSecurity.convertStringToMd5(this.senhanova));
					changed = true;
				} else {
					msg = "Verifique se seus endereços de e-mail são iguais e tente novamente.";
				}
			}
		} else {
			msg = "Ocorreu um problema com sua Senha";
		}
		if (changed) {
			Usuario usersave = usuarioDao.merge(this.usuario);
			this.usuario = usersave;
			return "conta.jsf";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Erro ao salvar", msg));
			return "";
		}
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
