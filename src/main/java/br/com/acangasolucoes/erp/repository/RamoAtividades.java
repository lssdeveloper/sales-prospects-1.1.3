package br.com.acangasolucoes.erp.repository;

import br.com.acangasolucoes.erp.model.RamoAtividade;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

//	public RamoAtividades() {
//
//	}
//
//	public RamoAtividades(EntityManager manager) {
//		this.manager = manager;
//	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);		
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);			
		criteriaQuery.select(root);				
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));		
		
		TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	public RamoAtividade porId(Long id){
		return manager.find(RamoAtividade.class, id);
	}
	public List<RamoAtividade> todas(){
		return manager.createQuery("from RamoAtividade", RamoAtividade.class).getResultList();
	}
	public RamoAtividade salvar(RamoAtividade ramoAtividade){
		return manager.merge(ramoAtividade);
	}
	public void excluir(RamoAtividade ramoAtividade){
		ramoAtividade = porId(ramoAtividade.getId());
		manager.remove(ramoAtividade);
	}
}

