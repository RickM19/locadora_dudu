package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;

public interface UserService extends GenericService<Usuario> {
    void fazerLogin(Usuario u);
    void deslogar();
    Usuario buscarPorId(Usuario u);
    void alterarSenha(Usuario u);
    void excluir(Usuario u);
}
