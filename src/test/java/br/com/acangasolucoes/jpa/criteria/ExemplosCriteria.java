package br.com.acangasolucoes.jpa.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.acangasolucoes.erp.model.Prospecto;

public class ExemplosCriteria {
	
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	
	@BeforeClass	
	public static void init(){
		factory = Persistence.createEntityManagerFactory("AcangaPU");
	}
	@Before
	public void setUp(){
		this.manager = factory.createEntityManager();
		
	}
	@After
	public void tearDown(){
		this.manager.close();
	}		
	
	@Test
	public void projecoes(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class); 
		
		Root<Prospecto> prospecto = criteriaQuery.from(Prospecto.class);
		criteriaQuery.select(prospecto.<String>get("empresa"));
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> empresas = query.getResultList();
		
		for (String empresa : empresas) {
			
			System.out.println(empresa);
			
		}

	}

}
