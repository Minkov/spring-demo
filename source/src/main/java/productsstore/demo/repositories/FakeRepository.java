package productsstore.demo.repositories;

import productsstore.demo.entities.base.ModelEntity;
import productsstore.demo.repositories.base.GenericRepository;

import java.util.List;

public class FakeRepository<T extends ModelEntity> implements GenericRepository<T> {
    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public T create(T entity) {
        return null;
    }
}
