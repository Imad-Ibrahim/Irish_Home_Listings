package Database;

import java.util.List;
import java.util.Optional;

public interface IrishHomeListingsRepository<T> {
    List<T> getAll();
    Optional<T> getByID(int id);
    void insert(T t);
    void update(T t);
    void delete(T t);
}
