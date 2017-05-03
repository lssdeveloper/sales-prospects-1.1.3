package br.com.acangasolucoes.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.acangasolucoes.erp.model.TipoProspecto;
import br.com.acangasolucoes.erp.repository.TipoProspectos;
import br.com.acangasolucoes.erp.service.CadastroTipoProspectoService;
import br.com.acangasolucoes.erp.util.FacesMessages;

@Named
@ViewScoped
public class TipoProspectosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProspectos tipoProspectos;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroTipoProspectoService cadastroTipoProspectoService ; 
	
	private List<TipoProspecto> listaTipoProspectos;
	
	private String termoPesquisa;
	
	private TipoProspecto tipoProspecto;

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public TipoProspecto getTipoProspecto() {
		return tipoProspecto;
	}

	public void setTipoProspecto(TipoProspecto tipoProspecto) {
		this.tipoProspecto = tipoProspecto;
	}

	public List<TipoProspecto> getListaTipoProspectos() {
		return listaTipoProspectos;
	}
	
	public void prepararNovoTipoProspecto(){
		
		tipoProspecto = new TipoProspecto();
	}
	public void pesquisar(){
		
		listaTipoProspectos = tipoProspectos.pesquisar(termoPesquisa);
		
		if (listaTipoProspectos.isEmpty()){
			messages.info("Sua consulta não retornou registros.");
		}
		
	}
	public void todosTipoProspectos(){
		listaTipoProspectos = tipoProspectos.todos();
	}
	public boolean jaHouvePesquisa(){
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	public void atualizaRegistros(){
		if (jaHouvePesquisa()) {
			pesquisar();
		}else{
			todosTipoProspectos();
		}
	}
	public void salvar(){
		 
		cadastroTipoProspectoService.salvar(tipoProspecto);
		
		atualizaRegistros();
		
		messages.info("Tipo de prospecto salvo com sucesso.");
		
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:tipoProspectosDataTable", "frm:messages"));
	}
	public void excluir(){
		
		cadastroTipoProspectoService.excluir(tipoProspecto);
		
		tipoProspecto = null;
		
		atualizaRegistros();
		
		messages.info("Tipo de prospecto excluído com sucesso.");
	}
	public boolean isTipoProspectoSeleciona(){
		return tipoProspecto != null && tipoProspecto.getId() != null;
	}
}
