package br.com.danilloparreira.gerenciador.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "perfil")
	private List<Usuario> usuarios;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<CadastroAcao> cadastroAcoes;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<RelatorioAcao> relatorioAcoes;

	public Perfil() {
		super();
		this.usuarios = new ArrayList<>();
		cadastroAcoes = new ArrayList<>();
		Arrays.asList(EnumCadastros.values()).forEach(r -> cadastroAcoes.add(new CadastroAcao(r)));
		relatorioAcoes = new ArrayList<>();
		Arrays.asList(EnumRelatorios.values()).forEach(r -> relatorioAcoes.add(new RelatorioAcao(r)));
	}

	public Perfil(String descricao) {
		super();
		this.descricao = descricao;
		this.usuarios = new ArrayList<>();
		cadastroAcoes = new ArrayList<>();
		Arrays.asList(EnumCadastros.values()).forEach(r -> cadastroAcoes.add(new CadastroAcao(r)));
		relatorioAcoes = new ArrayList<>();
		Arrays.asList(EnumRelatorios.values()).forEach(r -> relatorioAcoes.add(new RelatorioAcao(r)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<CadastroAcao> getCadastroAcoes() {
		return cadastroAcoes;
	}

	public void setCadastroAcoes(List<CadastroAcao> cadastroAcoes) {
		this.cadastroAcoes = cadastroAcoes;
	}

	public List<RelatorioAcao> getRelatorioAcoes() {
		return relatorioAcoes;
	}

	public void setRelatorioAcoes(List<RelatorioAcao> relatorioAcoes) {
		this.relatorioAcoes = relatorioAcoes;
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
