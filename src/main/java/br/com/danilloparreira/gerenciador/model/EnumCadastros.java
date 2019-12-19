package br.com.danilloparreira.gerenciador.model;

public enum EnumCadastros {
	USUARIO("Usuario"), PERFIL("perfil");

	private String descricao;

	private EnumCadastros(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
