package Database;


import Model.Style;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class StyleDB implements IrishHomeListingsRepository<Style>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Style> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT style from Style style";
        TypedQuery<Style> tq = em.createQuery(q, Style.class);
        List<Style> list;
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
    public Optional<Style> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        Style style = em.find(Style.class, id);
        em.close();
        return Optional.of(style);
    }

    @Override
    public void insert(Style style) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(style);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Style style) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(style);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Style style) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(style));
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

