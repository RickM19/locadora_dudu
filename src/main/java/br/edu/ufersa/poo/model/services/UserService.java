package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Usuario;

import java.util.List;

public interface UserService extends GenericService<Usuario> {
    void fazerLogin(String nome, String senha);
    void deslogar();
    Usuario buscarPorId(long id);
    void alterarSenha(long id, String novaSenha);
    void excluir(long id);
}
