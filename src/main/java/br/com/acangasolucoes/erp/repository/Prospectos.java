package br.com.acangasolucoes.erp.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.acangasolucoes.erp.model.Prospecto;
import br.com.acangasolucoes.erp.repository.filter.ProspectoFilter;

public class Prospectos implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private EntityManager manager;


	public Prospecto porId(Long id) {
		return manager.find(Prospecto.class, id);
	}
	public Prospecto salvar(Prospecto prospecto) {
		return manager.merge(prospecto);
	}


	
	public List<Prospecto> buscarTodos(){
		return manager.createQuery("from Prospecto", Prospecto.class).getResultList();
	}	
	
	 public List<Prospecto> filtrados(ProspectoFilter filtro) {
	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<Prospecto> criteriaQuery = builder.createQuery(Prospecto.class);
	        List<Predicate> predicates = new ArrayList<>();
	        Root<Prospecto> ProspectoRoot = criteriaQuery.from(Prospecto.class);
	        ProspectoRoot.fetch("usuario");
	        
	        From<? , ?> empresaJoin = (From<? , ?>) ProspectoRoot.fetch("empresa");
	        
	        ProspectoRoot.fetch("contato");

	        if (filtro.getDataCadastroDe() != null) {
	            predicates.add(builder.greaterThanOrEqualTo(ProspectoRoot.get("dataCadastro"), filtro.getDataCadastroDe()));
	        }

	        if (filtro.getDataCadastroAte() != null) {
	            predicates.add(builder.lessThanOrEqualTo(ProspectoRoot.get("dataCadastro"), filtro.getDataCadastroAte()));
	        }


	        if (StringUtils.isNotBlank(filtro.getEmpresa())) {
	            predicates.add(builder.like(empresaJoin.get("razaoSocial"), "%" + filtro.getEmpresa() + "%"));         
	        }


	        criteriaQuery.select(ProspectoRoot);
	        criteriaQuery.where(predicates.toArray(new Predicate[0]));
	        TypedQuery<Prospecto> query = manager.createQuery(criteriaQuery);
	        return query.getResultList();
	    }	
	


}
