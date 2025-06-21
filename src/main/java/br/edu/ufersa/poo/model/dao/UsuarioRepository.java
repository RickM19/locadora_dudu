package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario findById(long id);
    List<Usuario> findAll();
    void save(Usuario u);
    void update(Usuario u);
    void delete(Usuario u);
    void findByUserName(String userName);

    Usuario findById()
}
