package br.com.acangasolucoes.erp.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class ProspectoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private String nomeUsuario;
	private String nomeContato;
	private String tipoProspecto;
	private String empresa;

	public Date getDataCadastroDe() {
		return dataCadastroDe;
	}

	public void setDataCadastroDe(Date dataCadastroDe) {
		this.dataCadastroDe = dataCadastroDe;
	}

	public Date getDataCadastroAte() {
		return dataCadastroAte;
	}

	public void setDataCadastroAte(Date dataCadastroAte) {
		this.dataCadastroAte = dataCadastroAte;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTipoProspecto() {
		return tipoProspecto;
	}

	public void setTipoProspecto(String tipoProspecto) {
		this.tipoProspecto = tipoProspecto;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
