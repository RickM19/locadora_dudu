package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.dao.LivroRepository;
import br.edu.ufersa.poo.model.dao.LivroRepositoryImpl;
import br.edu.ufersa.poo.model.entities.Livro;
import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.model.enums.TipoUsuario;
import br.edu.ufersa.poo.util.Session;

import java.util.List;

public class LivroServiceImpl implements LivroService {
    private final LivroRepository livroRepo = new LivroRepositoryImpl();

    @Override
    public Livro buscarPorId(long id) {
        return livroRepo.findById(id);
    }
    @Override
    public List<Livro> listarTodos() {
        return livroRepo.findAll();
    }
    @Override
    public Livro buscarPorTitulo(String titulo) {
        if(titulo != null && !titulo.isEmpty()) {
            return livroRepo.findByTitle(titulo);
        }
        return null;
    }
    @Override
    public Livro buscarPorAutor(String autor) {
        if(autor != null && !autor.isEmpty()) {
            return livroRepo.findByAuthor(autor);
        }
        return null;
    }
    @Override
    public Livro buscarPorGenero(String genero) {
        if(genero != null && !genero.isEmpty()) {
            return livroRepo.findByGenre(genero);
        }
        return null;
    }
    @Override
    public Livro buscarPorAno(int ano) {
        if(ano > 0) {
            return livroRepo.findByYear(ano);
        }
        return null;
    }
    @Override
    public void cadastrar(Livro l) {
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findByTitle(l.getTitulo());
        if(livroEncontrado != null && livroEncontrado.getTitulo().equals(l.getTitulo())){
            throw new IllegalArgumentException("O livro informado ja está cadastrado!");
        }
        livroRepo.save(l);
    }
    @Override
    public void excluir(long id) {
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findById(id);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroRepo.delete(livroEncontrado);
    }
    @Override
    public void alterarEstoque(long id, int qtd) {
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Livro livroEncontrado = livroRepo.findById(id);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroEncontrado.setQtdExemplares(qtd);

    }
    @Override
    public void alugar(long id) {
        Livro livroEncontrado = livroRepo.findById(id);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroEncontrado.alugar();
        livroRepo.update(livroEncontrado);
    }
    @Override
    public void devolver(long id) {
        Livro livroEncontrado = livroRepo.findById(id);
        if(livroEncontrado == null) {
            throw new IllegalArgumentException("Livro inexistente!");
        }
        livroEncontrado.devolver();
        livroRepo.update(livroEncontrado);
    }

}
