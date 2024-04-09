package Database;


import Model.Property;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PropertyDB implements IrishHomeListingsRepository<Property> {
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Property> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT property from Property property";
        TypedQuery<Property> tq = em.createQuery(q, Property.class);
        List<Property> list;
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
    public Optional<Property> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        Property property = em.find(Property.class, id);
        em.close();
        return Optional.of(property);
    }

    @Override
    public void insert(Property property) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(property);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Property property) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(property);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Property property) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(property));
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
