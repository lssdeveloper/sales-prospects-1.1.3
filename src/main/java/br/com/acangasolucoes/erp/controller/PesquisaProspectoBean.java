package br.com.acangasolucoes.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.acangasolucoes.erp.model.Empresa;
import br.com.acangasolucoes.erp.model.Prospecto;
import br.com.acangasolucoes.erp.repository.Empresas;
import br.com.acangasolucoes.erp.repository.Prospectos;
import br.com.acangasolucoes.erp.repository.filter.ProspectoFilter;
import br.com.acangasolucoes.erp.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaProspectoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Prospectos prospectos;
	@Inject
	private Empresas empresas;	
	@Inject
	private FacesMessages messages;	

	private ProspectoFilter filtro;

	private Prospecto prospecto;

	private List<Prospecto> prospectosFiltrados;

	private List<Empresa> listaEmpresas;

	public PesquisaProspectoBean() {
		
		prospecto = new Prospecto();
		filtro = new ProspectoFilter();
		prospectosFiltrados = new ArrayList<>();
		this.listaEmpresas = new ArrayList<>();
		
	}

	@PostConstruct
	private void init() {
		this.listaEmpresas = this.empresas.todas();
	}
	
	public void inicializar(){
		
	}

	public void pesquisar() {
		prospectosFiltrados = prospectos.filtrados(filtro);
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public List<Prospecto> getProspectosFiltrados() {
		return prospectosFiltrados;
	}

	public ProspectoFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(ProspectoFilter filtro) {
		this.filtro = filtro;
	}

	public Prospecto getProspecto() {
		return prospecto;
	}
	public void setProspecto(Prospecto prospecto) {
		this.prospecto = prospecto;
	}
	public void onRowSelect(SelectEvent event) {
        messages.info(((Prospecto) event.getObject()).getObservacao());
    }
 
    public void onRowUnselect(UnselectEvent event) {
        messages.info(((Prospecto) event.getObject()).getObservacao());   	
    }
}
