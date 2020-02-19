package br.com.danilloparreira.gerenciador.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.danilloparreira.gerenciador.model.enuns.EnumCadastros;

@Entity
public class CadastroAcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EnumCadastros enumCadastros;

	private Boolean listar;
	private Boolean criar;
	private Boolean editar;
	private Boolean remover;

	@Transient
	private int alternaSelecao;

	public CadastroAcao() {
		super();
	}

	public CadastroAcao(EnumCadastros enumCadastros) {
		super();
		this.enumCadastros = enumCadastros;
		alternarValoreDosBoolean(false);
		updateAlternarSelecao();
	}

	public CadastroAcao(Long id, EnumCadastros enumCadastros, Boolean listar, Boolean criar, Boolean editar,
			Boolean remover, int alternaSelecao) {
		super();
		this.id = id;
		this.enumCadastros = enumCadastros;
		this.listar = listar;
		this.criar = criar;
		this.editar = editar;
		this.remover = remover;
		this.alternaSelecao = alternaSelecao;
		updateAlternarSelecao();
	}

	private void alternarValoreDosBoolean(Boolean condicao) {
		this.listar = condicao;
		this.criar = condicao;
		this.editar = condicao;
		this.remover = condicao;
	}

	public void updateAlternarSelecao() {
		if (verificaTodasAcoes(true)) {
			this.alternaSelecao = 2;
		} else if (verificaTodasAcoes(false)) {
			this.alternaSelecao = 0;
		} else {
			this.alternaSelecao = 1;
		}
	}

	private boolean verificaTodasAcoes(Boolean condicao) {
		return this.listar == condicao && this.editar == condicao && this.remover == condicao && this.criar == condicao;
	}

	public void setAlternaSelecao(int alternaSelecao) {
		this.alternaSelecao = alternaSelecao;
	}
	public void clickAlternarSelecao() {
		if (this.alternaSelecao == 0) {
			alternarValoreDosBoolean(false);
		} else if (this.alternaSelecao == 1 || this.alternaSelecao == 2) {
			alternarValoreDosBoolean(true);
			this.alternaSelecao = 2;
		}
	}
	public int getAlternaSelecao() {
		return alternaSelecao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumCadastros getEnumCadastros() {
		return enumCadastros;
	}

	public void setEnumCadastros(EnumCadastros enumCadastros) {
		this.enumCadastros = enumCadastros;
	}

	public Boolean getListar() {
		return listar;
	}

	public void setListar(Boolean listar) {
		this.listar = listar;
		updateAlternarSelecao();
	}

	public Boolean getCriar() {
		return criar;
	}

	public void setCriar(Boolean criar) {
		this.criar = criar;
		updateAlternarSelecao();
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
		updateAlternarSelecao();
	}

	public Boolean getRemover() {
		return remover;
	}

	public void setRemover(Boolean remover) {
		this.remover = remover;
		updateAlternarSelecao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroAcao other = (CadastroAcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
