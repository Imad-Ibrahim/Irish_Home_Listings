package Database;

import Model.Propertynotes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PropertynotesDB implements IrishHomeListingsRepository<Propertynotes>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Propertynotes> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT pn from Propertynotes pn";
        TypedQuery<Propertynotes> tq = em.createQuery(q, Propertynotes.class);
        List<Propertynotes> list;
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
    public Optional<Propertynotes> getByID(int id) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        Propertynotes propertynotes = em.find(Propertynotes.class, id);
        em.close();
        return Optional.of(propertynotes);
    }

    @Override
    public void insert(Propertynotes propertynotes) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(propertynotes);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Propertynotes propertynotes) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(propertynotes);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Propertynotes propertynotes) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(propertynotes));
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
