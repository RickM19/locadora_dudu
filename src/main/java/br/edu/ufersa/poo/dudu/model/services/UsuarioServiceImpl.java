package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.UsuarioRepository;
import br.edu.ufersa.poo.dudu.model.dao.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.util.Session;

import java.util.List;

public class UsuarioServiceImpl implements UserService {
    private final UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl();

    @Override
    public void fazerLogin(String nome, String senha) {
        Usuario userEncontrado = usuarioRepo.findByUserName(nome);
        if(userEncontrado == null || !userEncontrado.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Nome de usuário ou senha inválidos!");
        }
        Session.setUsuarioLogado(userEncontrado);
    }

    public void deslogar() {
        Session.encerrarSessao();
    }

    @Override
    public Usuario buscarPorId(long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public void cadastrar(Usuario u) {
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
