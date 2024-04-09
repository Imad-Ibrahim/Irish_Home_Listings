package Database;


import Model.Propertytype;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PropertytypeDB implements IrishHomeListingsRepository<Propertytype>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Propertytype> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT propertytype from Propertytype propertytype";
        TypedQuery<Propertytype> tq = em.createQuery(q, Propertytype.class);
        List<Propertytype> list;
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
    public Optional<Propertytype> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        Propertytype propertytype = em.find(Propertytype.class, id);
        em.close();
        return Optional.of(propertytype);
    }

    @Override
    public void insert(Propertytype propertytype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(propertytype);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Propertytype propertytype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(propertytype);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Propertytype propertytype) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(propertytype));
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
