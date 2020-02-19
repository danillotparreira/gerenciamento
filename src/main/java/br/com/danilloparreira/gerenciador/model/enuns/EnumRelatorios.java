package br.com.danilloparreira.gerenciador.model.enuns;

public enum EnumRelatorios {
	USUARIO("Usuario"), PERFIL("Perfil");

	private String descricao;

	private EnumRelatorios(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
