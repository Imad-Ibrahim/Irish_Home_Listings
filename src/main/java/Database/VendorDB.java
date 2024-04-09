package Database;

import Model.Vendor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class VendorDB implements IrishHomeListingsRepository<Vendor>{
    EntityManager em;
    EntityTransaction trans;
    @Override
    public List<Vendor> getAll() {
        em = DBUtil.getEmf().createEntityManager();
        String q = "SELECT vendor from Vendor vendor";
        TypedQuery<Vendor> tq = em.createQuery(q, Vendor.class);
        List<Vendor> list;
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
    public Optional<Vendor> getByID(int id) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        Vendor vendor = em.find(Vendor.class, id);
        em.close();
        return Optional.of(vendor);
    }

    @Override
    public void insert(Vendor vendor) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(vendor);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Vendor vendor) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(vendor);
            trans.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Vendor vendor) {
        em = DBUtil.getEmf().createEntityManager();
        trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(vendor));
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
