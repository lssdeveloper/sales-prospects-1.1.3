package br.com.acangasolucoes.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.Empresa;
import br.com.acangasolucoes.erp.repository.Empresas;
import br.com.acangasolucoes.erp.util.jpa.Transactional;


public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Transactional
	public Empresa salvar(Empresa empresa) throws NegocioException {
		
		Empresa clone =  empresas.porCnpj(empresa.getCnpj()); 
		
        if (clone != null && empresa.getId() == null)
            throw new NegocioException("CNPJ " + empresa.getCnpj() + " já cadastrado.");		
  
		if (clone != null&& clone.getId() != null && !clone.equals(empresa))
            throw new NegocioException("Empresa já cadastrada.");
		   
		return empresas.salvar(empresa);
	}
	@Transactional
	public void excluir(Empresa empresa){
		empresas.excluir(empresa);
	}
}
