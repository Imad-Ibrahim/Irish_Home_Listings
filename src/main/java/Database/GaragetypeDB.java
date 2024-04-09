package Database;

import Model.Garagetype;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class GaragetypeDB implements IrishHomeListingsRepository<Garagetype>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Garagetype> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT garagetype from Garagetype garagetype";
        TypedQuery<Garagetype> tq = em.createQuery(q, Garagetype.class);
        List<Garagetype> list;
        try {
            list = tq.getResultList();
            if (list == null || list.isEmpty())
                list = null;

        } finally {
            em.close();
        }
        return list;
    }

    @Override
    public Optional<Garagetype> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        Garagetype garagetype = em.find(Garagetype.class, id);
        em.close();
        return Optional.of(garagetype);
    }

    @Override
    public void insert(Garagetype garagetype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(garagetype);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Garagetype garagetype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(garagetype);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Garagetype garagetype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(garagetype));
            trans.commit();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        finally {
            em.close();
        }
    }
}

