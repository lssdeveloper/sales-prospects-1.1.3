package br.com.acangasolucoes.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.Prospecto;
import br.com.acangasolucoes.erp.repository.Prospectos;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

public class CadastroProspectoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Prospectos prospectos;
	
	@Transactional
	public void salvar(Prospecto prospecto){
		prospectos.salvar(prospecto);
	}	

}
