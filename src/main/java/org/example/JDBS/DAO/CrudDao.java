package org.example.JDBS.DAO;

import org.example.JDBS.Entity.Customers;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    List<T> getAll();

    Optional<T> getById(Long id);

    T save(T entity);

    boolean update(T entity);

    boolean delete(Long id);
}
