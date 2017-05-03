package br.com.acangasolucoes.erp.repository.filter;

import java.io.Serializable;

public class ContatoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeContato;
    private String nomeEmpresa;
    
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
    
    

}
