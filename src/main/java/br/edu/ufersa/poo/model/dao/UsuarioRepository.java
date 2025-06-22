package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Usuario;

import java.util.List;

public interface UsuarioRepository extends GenericRepository<Usuario> {
    Usuario findById(long id);
    Usuario findByUserName(String userName);
}
