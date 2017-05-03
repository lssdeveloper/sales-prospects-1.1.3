package br.com.acangasolucoes.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.acangasolucoes.erp.model.TipoProspecto;

public class TipoProspectos implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Inject
	private EntityManager manager;
	
	public TipoProspectos(){
		
	}
	public TipoProspectos(EntityManager manager) {
		this.manager = manager;
	}
	public List<TipoProspecto> pesquisar(String descricao){
		
		String jpql = "from TipoProspecto where upper(descricao) like :descricao";
		
		TypedQuery<TipoProspecto> query = manager
				.createQuery(jpql, TipoProspecto.class);
		
		query.setParameter("descricao", descricao.toUpperCase() + "%");
		
		return query.getResultList();
		
	}
	public List<TipoProspecto> porDescricao(String descricao) {
		return this.manager.createQuery("from TipoProspecto where upper(descricao) like :descricao", TipoProspecto.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
	}
	public TipoProspecto porId(Long id){
		return manager.find(TipoProspecto.class, id);
	}
	public List<TipoProspecto> todos(){
		return manager.createQuery("from TipoProspecto", TipoProspecto.class).getResultList();
	}
	public TipoProspecto salvar(TipoProspecto tipoProspecto){
		return manager.merge(tipoProspecto);
	}
	public void excluir(TipoProspecto tipoProspecto){
		tipoProspecto = porId(tipoProspecto.getId());
		manager.remove(tipoProspecto);
	}

}
