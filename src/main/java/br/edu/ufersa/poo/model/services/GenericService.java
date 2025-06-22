package br.edu.ufersa.poo.model.services;

import java.util.List;

public interface GenericService<T> {
    List<T> listarTodos();
    void cadastrar(T entidade);
}
