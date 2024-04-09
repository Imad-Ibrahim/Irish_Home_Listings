package Database;

import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDB implements IrishHomeListingsRepository<User>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<User> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT user from User user";
        TypedQuery<User> tq = em.createQuery(q, User.class);
        List<User> list;
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
    public Optional<User> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return Optional.of(user);
    }

    @Override
    public void insert(User user) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(User user) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(user));
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