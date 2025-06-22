package br.edu.ufersa.poo.model.dao;

import java.util.List;

public interface GenericRepository<T> {
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
