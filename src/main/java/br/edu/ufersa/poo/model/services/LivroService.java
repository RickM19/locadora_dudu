package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Livro;

import java.util.List;

public interface LivroService extends GenericService<Livro> {
    Livro buscarPorId(long id);
    Livro buscarPorTitulo(String titulo);
    Livro buscarPorAutor(String Autor);
    Livro buscarPorGenero(String Genero);
    Livro buscarPorAno(int ano);
    void excluir(long id);
    void alterarEstoque(long id, int qtd);
    void alugar(long id);
    void devolver(long id);
}
