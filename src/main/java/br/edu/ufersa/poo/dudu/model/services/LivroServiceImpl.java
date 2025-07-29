package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.LivroRepository;
import br.edu.ufersa.poo.dudu.model.dao.LivroRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Livro;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.model.enums.TipoUsuario;
import br.edu.ufersa.poo.dudu.util.Session;

import java.util.List;

public class LivroServiceImpl implements LivroService {
    private final LivroRepository livroRepo = new LivroRepositoryImpl();
    private final Session sessionInstance = Session.getInstance();

    @Override
    public Livro buscarPorId(Livro l) {
        return livroRepo.findById(l);
    }
    @Override
    public List<Livro> listarTodos() {
        return livroRepo.findAll();
    }
    @Override
    public Livro buscarPorTitulo(Livro l) {
        if(l.getTitulo() != null && !l.getTitulo().isEmpty()) {
            return livroRepo.findByTitle(l);
        }
        return null;
    }
    @Override
    public Livro buscarPorAutor(Livro l) {
        if(l.getAutor() != null && !l.getAutor().isEmpty()) {
            return livroRepo.findByAuthor(l);
        }
        return null;
    }
    @Override
    public Livro buscarPorGenero(Livro l) {
        if(l.getCategoria() != null && !l.getCategoria().isEmpty()) {
            return livroRepo.findByGenre(l);
        }
        return null;
    }
    @Override
    public Livro buscarPorAno(Livro l) {
        if(l.getAnoPublicacao() > 0) {
            return livroRepo.findByYear(l);
        }
        return null;
    }
    @Override
    public void cadastrar(Livro l) {
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if (usuarioLogado == null || usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findByTitle(l);
        if(livroEncontrado != null && livroEncontrado.getAutor().equals(l.getAutor())){
            throw new IllegalArgumentException("O livro informado ja está cadastrado!");
        }
        livroRepo.save(l);
    }
    @Override
    public void excluir(Livro l) {
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        System.out.println(usuarioLogado);
        if (usuarioLogado == null || usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findById(l);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroRepo.delete(livroEncontrado);
    }
    @Override
    public void alterarEstoque(Livro l) {
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if (usuarioLogado == null || usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findById(l);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        int antigaQtdExemplares = livroEncontrado.getExemplares();
        int diferenca = l.getExemplares() - antigaQtdExemplares;
        int novosDisponiveis = livroEncontrado.getQtdDisponivelAluguel() + diferenca;
        int novaQtdDisponivelAluguel = Math.max(novosDisponiveis, 0);

        livroEncontrado.setQtdExemplares(l.getExemplares());
        livroEncontrado.setQtdDisponivelAluguel(novaQtdDisponivelAluguel);
        livroRepo.update(livroEncontrado);
    }
    @Override
    public void alugar(Livro l) {
        Livro livroEncontrado = livroRepo.findById(l);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroEncontrado.alugar();
        livroRepo.update(livroEncontrado);
    }
    @Override
    public void devolver(Livro l) {
        Livro livroEncontrado = livroRepo.findById(l);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroEncontrado.devolver();
        livroRepo.update(livroEncontrado);
    }

}
