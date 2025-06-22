package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Usuario;

import java.util.List;

public interface UserService {
    void fazerLogin(String nome, String senha);
    void deslogar();
    Usuario buscarPorId(long id);
    List<Usuario> ListarTodos();
    void registrar(Usuario u);
    void alterarSenha(long id, String novaSenha);
    void excluir(long id);
}
