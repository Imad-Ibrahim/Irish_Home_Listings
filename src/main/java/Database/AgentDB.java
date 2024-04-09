package Database;


import Model.Agent;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AgentDB implements IrishHomeListingsRepository<Agent>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Agent> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT agent from Agent agent";
        TypedQuery<Agent> tq = em.createQuery(q, Agent.class);
        List<Agent> list;
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
    public Optional<Agent> getByID(int id) {
        em = DBUtil.getEmf().createEntityManager();
        Agent agent = em.find(Agent.class, id);
        em.close();
        return Optional.of(agent);
    }

    @Override
    public void insert(Agent agent) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(agent);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Agent agent) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(agent);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Agent agent) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(agent));
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
