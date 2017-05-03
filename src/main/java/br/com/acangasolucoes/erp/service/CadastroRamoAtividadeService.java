package br.com.acangasolucoes.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.RamoAtividade;
import br.com.acangasolucoes.erp.repository.RamoAtividades;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

public class CadastroRamoAtividadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Transactional
	public void salvar(RamoAtividade ramoAtividade){
		ramoAtividades.salvar(ramoAtividade);
	}
	@Transactional
	public void excluir(RamoAtividade ramoAtividade){
		ramoAtividades.excluir(ramoAtividade);
	}
}
