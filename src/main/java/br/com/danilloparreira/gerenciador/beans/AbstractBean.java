package br.com.danilloparreira.gerenciador.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public abstract class AbstractBean {

	protected String descricao;

	public AbstractBean(String descricao) {
		super();
		this.descricao = descricao;
	}

	public void reset() {
		PrimeFaces.current().resetInputs("form:cadastro");
	}

	public void msgSalvar() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(descricao + " cadastrado(a) com sucesso!"));
	}

	public void msgDeletar() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(descricao + " foi deletado com sucesso!"));
	}

	public void msg(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	public void msgError(String title, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
	}
}
