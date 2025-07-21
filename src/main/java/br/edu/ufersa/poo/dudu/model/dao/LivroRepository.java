package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Livro;

public interface LivroRepository extends GenericRepository<Livro> {
    Livro findById(long id);
    Livro findByTitle(String title);
    Livro findByGenre(String genre);
    Livro findByYear(int year);
    Livro findByAuthor(String Author);
}
