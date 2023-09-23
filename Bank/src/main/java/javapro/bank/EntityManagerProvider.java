package javapro.bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class EntityManagerProvider {

    private static EntityManagerFactory emf = createEntityManagerFactory("Bank");
    private static EntityManager em = emf.createEntityManager();


    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static EntityManager getEm() {
        return em;
    }
}
