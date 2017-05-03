package br.com.acangasolucoes.erp.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import br.com.acangasolucoes.erp.model.Prospeccao;
import br.com.acangasolucoes.erp.model.RamoAtividade;
//import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.model.Empresa;

public class SchemaGeneration {
	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AcangaPU");
		
		EntityManager em = emf.createEntityManager();
		
		List<Empresa> lista = em.createQuery(" from Empresa", Empresa.class).getResultList();
		List<RamoAtividade> lstRA = em.createQuery("from RamoAtividade", RamoAtividade.class).getResultList(); 
/*		List<Contato> listContato = em.createQuery(" from Contato", Contato.class).getResultList();
		List<Prospeccao> listAcao = em.createQuery(" from Prospeccao", Prospeccao.class).getResultList();
	

		System.out.println(listContato);
		System.out.println(listAcao);	
		
		
*/
		
		System.out.println(lista);	
		System.out.println(lstRA);
		em.close();
		emf.close();
	}
}
