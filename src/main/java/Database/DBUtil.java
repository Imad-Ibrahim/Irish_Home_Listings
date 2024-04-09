package Database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("irish_home_listings");
    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
