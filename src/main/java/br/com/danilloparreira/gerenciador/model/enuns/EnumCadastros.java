package br.com.danilloparreira.gerenciador.model.enuns;

public enum EnumCadastros {
	USUARIO("Usuario"), PERFIL("Perfil");

	private String descricao;

	private EnumCadastros(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
