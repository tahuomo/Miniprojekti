package kumiankka.viitejarjestelma;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Viitetietokanta implements Viitehallinta {
    private EntityManagerFactory emf = null;

    public Viitetietokanta() {
        emf = Persistence.createEntityManagerFactory("viitteet");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void tallennaViite(Viite viite) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(viite);
        em.getTransaction().commit();
    }

    @Override
    public List<Viite> listaaViitteet() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT v FROM Viite v").getResultList();
    }

    @Override
    public Viite etsiTunniste(String tunniste) {
        EntityManager em = getEntityManager();
        try {
            return (Viite) em.createQuery(
                    "SELECT v FROM Viite v "
                    + "WHERE v.tunniste LIKE :tunniste")
                    .setParameter("tunniste", tunniste)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
}
