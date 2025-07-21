package br.edu.ufersa.poo.dudu.model.services;

import java.util.List;

public interface GenericService<T> {
    List<T> listarTodos();
    void cadastrar(T entidade);
}
