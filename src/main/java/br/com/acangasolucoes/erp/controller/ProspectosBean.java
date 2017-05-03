package br.com.acangasolucoes.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.acangasolucoes.erp.converter.TipoProspectoConverter;
import br.com.acangasolucoes.erp.converter.UsuarioConverterLst;
import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.model.Empresa;
import br.com.acangasolucoes.erp.model.Prospecto;
import br.com.acangasolucoes.erp.model.TipoProspecto;
import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.Contatos;
import br.com.acangasolucoes.erp.repository.Empresas;
import br.com.acangasolucoes.erp.repository.TipoProspectos;
import br.com.acangasolucoes.erp.repository.Usuarios;
import br.com.acangasolucoes.erp.security.UsuarioLogado;
import br.com.acangasolucoes.erp.security.UsuarioSistema;
import br.com.acangasolucoes.erp.service.CadastroProspectoService;
import br.com.acangasolucoes.erp.service.NegocioException;
import br.com.acangasolucoes.erp.util.jsf.FacesUtil;

@Named
@SessionScoped
public class ProspectosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Inject
	private Empresas empresas;
	@Inject
	private Contatos contatos;
	@Inject
	private Usuarios usuarios;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioSistema;

	private Converter usuarioConverterLst;
	@Inject
	private TipoProspectos tipoProspectos;

	private Converter tipoProspectoConverter;  
	@Inject
	private CadastroProspectoService cadastroProspectoService;

	private Prospecto prospecto;
	private Empresa empresa;
	private Usuario usuario;
	private Contato contato;
	
	private TipoProspecto tipoProspecto ;
	private List<Usuario> usuariosOpcoes;
	private List<TipoProspecto> tiposDeProspectosOpcoes;
	private List<Empresa> empresasOpcoes;
	private List<Contato> contatosOpcoes;

	public ProspectosBean() {
		prospecto = new Prospecto();
		prospecto.setDataCadastro(Calendar.getInstance().getTime());
		empresasOpcoes = new ArrayList<>();
		contatosOpcoes = new ArrayList<>();
		usuariosOpcoes = new ArrayList<>();
		tiposDeProspectosOpcoes = new ArrayList<>();
		
	}

	@PostConstruct
	public void inicializar() {


		 if (prospecto == null) { 
			 prospecto = new Prospecto();
	 
		 }
		 prospecto.setDataCadastro(Calendar.getInstance().getTime()); 		
		
		this.empresasOpcoes = empresas.todas();
		this.usuariosOpcoes = this.usuarios.todos();
		this.tiposDeProspectosOpcoes = this.tipoProspectos.todos();
		this.prospecto.setUsuario((usuarioSistema.getUsuario()));

	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void setTipoProspecto(TipoProspecto tipoProspecto) {
		this.tipoProspecto = tipoProspecto;
	}

	public Contato getContato() {
		return contato;
	}
	public TipoProspecto getTipoProspecto() {
		return tipoProspecto;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Empresa> getEmpresasOpcoes() {
		return empresasOpcoes;
	}

	public List<Contato> getContatosOpcoes() {
		return contatosOpcoes;
	}

	public List<Usuario> getUsuariosOpcoes() {
		return usuariosOpcoes;
	}
	public List<TipoProspecto> getTiposDeProspectosOpcoes() {
		return tiposDeProspectosOpcoes;
	}


	public Empresas getEmpresas() {
		return empresas;
	}

	public Contatos getContatos() {
		return contatos;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public TipoProspectos getTipoProspectos() {
		return tipoProspectos;
	}

	public Prospecto getProspecto() {
		return prospecto;
	}

	public void setProspecto(Prospecto prospecto) {
		this.prospecto = prospecto;

	}
	public Converter getUsuarioConverter() {
		return usuarioConverterLst;
	}
	public Converter getTipoProspectoConverter() {
		return tipoProspectoConverter;
	}
    private void limpar() {
        prospecto = new Prospecto();
        empresasOpcoes = new ArrayList<>();
        contatosOpcoes = new ArrayList<>();
        usuariosOpcoes = new ArrayList<>();
        tiposDeProspectosOpcoes = new ArrayList<>();
    	this.empresasOpcoes = empresas.todas();
    }	

	public void salvar() throws NegocioException {

		try {

				cadastroProspectoService.salvar(prospecto);
				FacesUtil.addInfoMessage("Prospecto salvo com sucesso.");
				RequestContext.getCurrentInstance().update(Arrays.asList("frm:prospectoEmpresa", "frm:prospectoContato", "frm:usuario", "frm:tipo", "frm:obs"));
				limpar();			
		

		} catch (NegocioException e) {
			 FacesUtil.addErrorMessage(e.getMessage());
		}	


	}
	public void carregarContatos(){
		contatosOpcoes = contatos.contatosDaEmpresa(prospecto.getEmpresa());
	}

	public List<Usuario> completarUsuario(String termo) {		
		List<Usuario> listaUsuarios = usuarios.pesquisar(termo);		
		usuarioConverterLst = new UsuarioConverterLst(listaUsuarios);
		return listaUsuarios;
	}
	public List<TipoProspecto> completarTipoProspecto(String termo) {
		List<TipoProspecto> listaTipoProspectos = tipoProspectos.pesquisar(termo);

		tipoProspectoConverter = new TipoProspectoConverter(listaTipoProspectos);

		return listaTipoProspectos;
	}	

}
