package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Livro;

public interface LivroService extends GenericService<Livro> {
    Livro buscarPorId(Livro l);
    Livro buscarPorTitulo(Livro l);
    Livro buscarPorAutor(Livro l);
    Livro buscarPorGenero(Livro l);
    Livro buscarPorAno(Livro l);
    void excluir(Livro l);
    void alterarEstoque(Livro l);
    void alugar(Livro l);
    void devolver(Livro l);
}
