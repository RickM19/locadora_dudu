package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;

public interface UserService extends GenericService<Usuario> {
    void fazerLogin(String nome, String senha);
    void deslogar();
    Usuario buscarPorId(long id);
    void alterarSenha(long id, String novaSenha);
    void excluir(long id);
}
