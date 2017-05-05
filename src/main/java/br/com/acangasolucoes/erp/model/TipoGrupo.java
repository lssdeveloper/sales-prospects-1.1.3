package br.com.acangasolucoes.erp.model;

public enum TipoGrupo {
	
	ADMINISTRADOR("Administradores"),
	COLABORADOR("Colaboradores");
	
	private String descrição;
	
	private TipoGrupo(String descricao){
		this.descrição = descricao;
	}

	public String getDescrição() {
		return descrição;
	}

}
