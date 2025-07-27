package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.UsuarioRepository;
import br.edu.ufersa.poo.dudu.model.dao.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.util.Session;

import java.util.List;

public class UsuarioServiceImpl implements UserService {
    private final UsuarioRepository usuarioRepo = new UsuarioRepositoryImpl();
    private final Session sessionInstance = Session.getInstance();

    @Override
    public void fazerLogin(Usuario u) {
        Usuario userEncontrado = usuarioRepo.findByUserName(u);
        if(userEncontrado == null || !userEncontrado.getSenha().equals(u.getSenha())) {
            throw new IllegalArgumentException("Nome de usuário ou senha inválidos!");
        }
        sessionInstance.setUsuarioLogado(userEncontrado);
    }

    public void deslogar() {
        sessionInstance.encerrarSessao();
    }

    @Override
    public Usuario buscarPorId(Usuario u) {
        return usuarioRepo.findById(u);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public void cadastrar(Usuario u) {
        if(usuarioRepo.findByUserName(u) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado!");
        }
        usuarioRepo.save(u);
    }

    @Override
    public void alterarSenha(Usuario u) {
        Usuario usuarioEncontrado = usuarioRepo.findById(u);
        if(usuarioEncontrado == null)
            throw new IllegalArgumentException("Usuário inexistente!");
        usuarioEncontrado.setSenha(u.getSenha());
        usuarioRepo.update(usuarioEncontrado);
    }

    @Override
    public void excluir(Usuario u) {
        Usuario usuarioEncontrado = usuarioRepo.findById(u);
        if(usuarioEncontrado == null)
            throw new IllegalArgumentException("Usuário inexistente!");
        usuarioRepo.delete(usuarioEncontrado);
    }
}
