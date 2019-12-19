package br.com.danilloparreira.gerenciador.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CadastroAcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name = "perfil_id")
//	private Perfil perfi;

	@Enumerated(EnumType.STRING)
	private EnumCadastros enumCadastros;

	private Boolean listar;
	private Boolean criar;
	private Boolean editar;
	private Boolean remover;

	public CadastroAcao() {
		super();
	}

	public CadastroAcao(EnumCadastros enumCadastros) {
		super();
		this.enumCadastros = enumCadastros;
		this.listar = false;
		this.criar = false;
		this.editar = false;
		this.remover = false;
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
	}

	public Boolean getCriar() {
		return criar;
	}

	public void setCriar(Boolean criar) {
		this.criar = criar;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Boolean getRemover() {
		return remover;
	}

	public void setRemover(Boolean remover) {
		this.remover = remover;
	}

//	public Perfil getPerfi() {
//		return perfi;
//	}
//
//	public void setPerfi(Perfil perfi) {
//		this.perfi = perfi;
//	}

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
