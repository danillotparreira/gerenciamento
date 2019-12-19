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
public class RelatorioAcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfi;

	@Enumerated(EnumType.STRING)
	private EnumRelatorios enumRelatorios;
	private Boolean visualizar;

	public RelatorioAcao() {
		super();
	}

	public RelatorioAcao(EnumRelatorios enumRelatorios) {
		super();
		this.enumRelatorios = enumRelatorios;
		this.visualizar = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRelatorios getEnumRelatorios() {
		return enumRelatorios;
	}

	public void setEnumRelatorios(EnumRelatorios enumRelatorios) {
		this.enumRelatorios = enumRelatorios;
	}

	public Boolean getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Perfil getPerfi() {
		return perfi;
	}

	public void setPerfi(Perfil perfi) {
		this.perfi = perfi;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioAcao other = (RelatorioAcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
