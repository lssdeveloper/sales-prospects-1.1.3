package br.com.acangasolucoes.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.acangasolucoes.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

//	public Empresas() {
//
//	}
//
//	public Empresas(EntityManager manager) {
//		this.manager = manager;
//	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa where upper(nomeFantasia) like :nome or upper(razaoSocial) like :nome";
		
		TypedQuery<Empresa> query = manager
				.createQuery(jpql, Empresa.class);
		
		query.setParameter("nome", nome.toUpperCase() + "%");
		
		return query.getResultList();
	}
	public List<Empresa> pesquisarRazaoSocial(String nome) {
		String jpql = "from Empresa where upper(razaoSocial) like :nome";
		
		TypedQuery<Empresa> query = manager
				.createQuery(jpql, Empresa.class);
		
		query.setParameter("nome", nome.toUpperCase() + "%");
		
		return query.getResultList();
	}
	//Este método é igual ao de cima mas por razões de padrão 
	//ao construir a tela de itemPrsopecto fiz dessa forma
	public List<Empresa> porRazaoSocial(String nome) {
		return this.manager.createQuery("from Empresa " +
				"where upper(razaoSocial) like :nome", Empresa.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	public List<Empresa> todas(){
		return manager.createQuery("from Empresa", Empresa.class).getResultList();
	}

	public Empresa salvar(Empresa empresa) {
		return manager.merge(empresa);
	}

	public void excluir(Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}
    public Empresa porCnpj(String cnpj) {
        try {
            return (Empresa) manager.createQuery("from Empresa where cnpj = :cnpj")
                    .setParameter("cnpj", cnpj)
                    .getSingleResult();
        } catch (NoResultException ignore) {

        }
        return null;
    }	
}