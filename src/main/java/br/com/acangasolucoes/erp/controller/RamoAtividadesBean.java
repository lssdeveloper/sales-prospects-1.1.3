package br.com.acangasolucoes.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.acangasolucoes.erp.model.RamoAtividade;
import br.com.acangasolucoes.erp.repository.RamoAtividades;
import br.com.acangasolucoes.erp.service.CadastroRamoAtividadeService;
import br.com.acangasolucoes.erp.util.FacesMessages;
@Named
@ViewScoped
public class RamoAtividadesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroRamoAtividadeService cadastroRamoAtividadeService;  
	
	private List<RamoAtividade> listaRamoAtividades;
	
	private String termoPesquisa;
	
	private RamoAtividade ramoAtividade;
	
	/*Inicio Get Set*/
	
	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}
	
	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public List<RamoAtividade> getListaRamoAtividades() {
		return listaRamoAtividades;
	}
	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	

	
	/*Fim Get Set*/
	
	public void prepararNovoRamoAtividade(){
		
		ramoAtividade = new RamoAtividade();
	}
	public void pesquisar(){
		listaRamoAtividades = ramoAtividades.pesquisar(termoPesquisa);
		
		if (listaRamoAtividades.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}
	public void todosRamoAtividades(){
		listaRamoAtividades = ramoAtividades.todas();
	}
	private void atualizaRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosRamoAtividades();
		}
	}
	public boolean jaHouvePesquisa(){
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public void salvar(){
		
		cadastroRamoAtividadeService.salvar(ramoAtividade);
		
		atualizaRegistros();
		
		messages.info("Ramo atividade salvo com sucesso.");
		
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:ramoAtividadesDataTable", "frm:messages"));		
		
	}
	public void excluir(){
		
		cadastroRamoAtividadeService.excluir(ramoAtividade);
		
		ramoAtividade = null;
		
		atualizaRegistros();
		
		messages.info("Ramo atividade excluído com sucesso!");
	}
	
	public boolean isRamoAtividadeSeleciona(){
		return ramoAtividade != null && ramoAtividade.getId() != null;
	}

}
