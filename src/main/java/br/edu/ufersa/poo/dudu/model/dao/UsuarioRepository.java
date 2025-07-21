package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario> {
    Usuario findById(long id);
    Usuario findByUserName(String userName);
}
