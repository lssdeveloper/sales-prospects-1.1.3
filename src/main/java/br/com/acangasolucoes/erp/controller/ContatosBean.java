package br.com.acangasolucoes.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.acangasolucoes.erp.converter.EmpresaConverter;
import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.model.Empresa;
import br.com.acangasolucoes.erp.repository.Contatos;
import br.com.acangasolucoes.erp.repository.Empresas;
import br.com.acangasolucoes.erp.service.CadastroContatoService;
import br.com.acangasolucoes.erp.util.FacesMessages;

@Named
@ViewScoped
public class ContatosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Contatos contatos;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private CadastroContatoService cadastroContatoService; 
	
	private Contato contato;
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	private Converter empresaConverter;
	
	private List<Contato> listaContatos;
	
	private String termoPesquisa;
	
	public void prepararNovoContato(){
		
		contato = new Contato();
		contato.setDataCadastro(Calendar.getInstance().getTime());

	}
	public void prepararEdicao(){
		empresaConverter = new EmpresaConverter();
	}
	public void salvar(){
		
		cadastroContatoService.salvar(contato);
		
		atualizaRegistros();
		
		messages.info("Contato salvo com sucesso.");
		
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:contatosDataTable", "frm:messages"));
		
	}
	public void excluir(){
		
		cadastroContatoService.excluir(contato);
		
		contato = null;
		
		atualizaRegistros();
		
		messages.info("Contato excluído com sucesso!");
	}
	public void pesquisar(){
		listaContatos = contatos.pesquisar(termoPesquisa);
		
		if (listaContatos.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
		
	}

	public List<Empresa> completarEmpresa(String termo){
		
		List<Empresa> listaEmpresas = empresas.pesquisarRazaoSocial(termo);
		
		empresaConverter = new EmpresaConverter();
		 
		return listaEmpresas;
	}
	
	private void atualizaRegistros(){
		if (jaHouvePesquisa()) {
			pesquisar();
		}else {
			todosContatos();
		}
	}
	private boolean jaHouvePesquisa(){
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public boolean isContatoSeleciona(){
		return contato != null && contato.getId() != null;		
	}
	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void todosContatos(){
		listaContatos = contatos.todos();		
	}

	public Empresas getEmpresas() {
		return empresas;
	}
	
	public Converter getEmpresaConverter() {
		return empresaConverter;
	}
	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	

}
