package br.com.acangasolucoes.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.model.Empresa;


public class Contatos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	

	public Contato porId(Long id){
		return manager.find(Contato.class, id);
	}
	public List<Contato> pesquisar(String nome){
		String jpql = "from Contato where upper(nome) like :nome or upper(email) like :nome";
		
		TypedQuery<Contato> query = manager.createQuery(jpql, Contato.class);
		
		query.setParameter("nome", nome.toUpperCase() + "%");
		
		return query.getResultList(); 
	}

	public List<Contato> todos(){
		return manager.createQuery("from Contato", Contato.class).getResultList();
	}
	public Contato salvar(Contato contato){
		return manager.merge(contato);
	}
	public void excluir(Contato contato){
		contato = porId(contato.getId());
		manager.remove(contato);		
	}
	public Contato buscarComEmpresaPeloId(Long id){
		return manager.createQuery("select c Contato c inner join fetch c.empresa where c.id = :id", Contato.class)
				.setParameter("id", id).getSingleResult();
	}
	
	//ProspectoBean
	public List<Contato> contatosDaEmpresa(Empresa empresa) {
		return manager.createQuery("from Contato c join fetch c.empresa where  c.empresa = :empresa", Contato.class)
				.setParameter("empresa", empresa).getResultList();		

	}
	/******************************************************************************/
	/******************************************************************************/	
		
//	public List<Contato> filtradas(ContatoFilter contatoFilter){
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Contato> criteriaQuery = builder.createQuery(Contato.class);
//		List<Predicate> predicates = new ArrayList<>();
//		Root<Contato> contatoRoot = criteriaQuery.from(Contato.class);
//		From<?, ?> empresaJoin = (From<?, ?>) contatoRoot.fetch("empresa", JoinType.INNER);
//		
//		if (StringUtils.isNotBlank(contatoFilter.getNomeContato())) {
//            predicates.add(builder.like(contatoRoot.get("nomeContato"), "%" + contatoFilter.getNomeContato() + "%"));
//        }
//
//        if (StringUtils.isNotBlank(contatoFilter.getNomeEmpresa())) {
//            predicates.add(builder.like(empresaJoin.get("nomeContato"), "%" + contatoFilter.getNomeEmpresa() + "%"));
//        }
//
//        criteriaQuery.select(contatoRoot);
//        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//        TypedQuery<Contato> query = manager.createQuery(criteriaQuery);
//        return query.getResultList();
//	}
//	
//	
//
//	@SuppressWarnings("unchecked")
//	public List<Contato> listarContatos(){
//		return manager.createQuery("from Contato c join fetch c.empresa").getResultList();
//	}
}