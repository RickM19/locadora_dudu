package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.dao.UsuarioRepository;
import br.edu.ufersa.poo.model.dao.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.model.entities.Usuario;

import java.util.List;

public class UsuarioServiceImpl implements UserService {
    private final UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl();

    @Override
    public Usuario buscarPorId(long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public List<Usuario> ListarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public void registrar(Usuario u) {
        if(usuarioRepo.findByUserName(u.getNomeUsuario()) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado!");
        }
        usuarioRepo.save(u);
    }

    @Override
    public void alterarSenha(long id, String novaSenha) {
        Usuario usuarioEncontrado = usuarioRepo.findById(id);
        if(usuarioEncontrado == null)
            throw new IllegalArgumentException("Usuário inexistente!");
        usuarioEncontrado.setSenha(novaSenha);
        usuarioRepo.update(usuarioEncontrado);
    }

    @Override
    public void excluir(long id) {
        Usuario usuarioEncontrado = usuarioRepo.findById(id);
        if(usuarioEncontrado == null)
            throw new IllegalArgumentException("Usuário inexistente!");
        usuarioRepo.delete(usuarioEncontrado);
    }
}
