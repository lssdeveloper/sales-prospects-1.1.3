package br.com.acangasolucoes.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.repository.Contatos;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

public class CadastroContatoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Contatos contatos;
	
	@Transactional
	public void salvar(Contato contato){
		contatos.salvar(contato);
	}
	@Transactional
	public void excluir(Contato contato){
		contatos.excluir(contato);
	}

}
