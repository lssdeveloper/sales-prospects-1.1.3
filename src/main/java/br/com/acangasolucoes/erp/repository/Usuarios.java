package br.com.acangasolucoes.erp.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.filter.UsuarioFilter;
import br.com.acangasolucoes.erp.service.NegocioException;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;	
	

	public Usuarios() {

	}

	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario porId(Long id){
		return manager.find(Usuario.class, id);		
	}	
	

	public List<Usuario> todos(){
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}	
	


	public List<Usuario> pesquisar(String nome){
	
		String jpql = "from Usuario where upper(nome) like :nome";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("nome", nome.toUpperCase() + "%");
		
		return query.getResultList();		
	}

	public Usuario salvar(Usuario usuario){
		return manager.merge(usuario);
	}
    @Transactional
    public void remover(Usuario usuarioSelecionado) throws NegocioException {
        try {
            Usuario usuario = porId(usuarioSelecionado.getId());
            this.manager.remove(usuario);
            this.manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuário não pode ser excluído");
        }
    }
	public List<Usuario> porNome(String nome) {
		return this.manager.createQuery("from Usuario " +
				"where upper(nome) like :nome", Usuario.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
    public Usuario porEmail(String email) {
        try {
            return ((Usuario) manager.createQuery("from Usuario where upper(email) = :email ")
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult());
        } catch (NoResultException ignore) {
        }
        return null;
    }	
    public List<Usuario> filtratos(UsuarioFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);


        if (StringUtils.isNotBlank(filtro.getNome())){
            predicates.add(builder.like(usuarioRoot.get("nome"), "%" + filtro.getNome() + "%"));
        }

        if (StringUtils.isNotBlank(filtro.getEmail())){
            predicates.add(builder.like(usuarioRoot.get("email"), "%" + filtro.getEmail() + "%"));
        }

        criteriaQuery.select(usuarioRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }    
	
	
}
