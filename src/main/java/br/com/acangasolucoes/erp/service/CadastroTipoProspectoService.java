package br.com.acangasolucoes.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.TipoProspecto;
import br.com.acangasolucoes.erp.repository.TipoProspectos;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

public class CadastroTipoProspectoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProspectos tipoProspectos;
	
	@Transactional
	public void salvar(TipoProspecto tipoProspecto){
		tipoProspectos.salvar(tipoProspecto);
	}
	@Transactional
	public void excluir(TipoProspecto tipoProspecto){
		tipoProspectos.excluir(tipoProspecto);
	}

}
