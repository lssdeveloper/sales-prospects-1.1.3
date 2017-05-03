package br.com.acangasolucoes.erp.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        factory = Persistence.createEntityManagerFactory("AcangaPU");
    }

    @Produces
    @RequestScoped
    public Session createEntityManager() {
        return (Session) factory.createEntityManager();
    }

    /**
     * Funcionará como um gatílio.
     * Quando a requisição terminar, então, o método é chamado e o manager é fechado!
     * @param manager
     */
    public void closeEntityManager(@Disposes Session manager) {
        manager.close();
    }
}
