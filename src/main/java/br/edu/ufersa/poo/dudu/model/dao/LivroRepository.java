package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Livro;

public interface LivroRepository extends GenericRepository<Livro> {
    Livro findById(Livro l);
    Livro findByTitle(Livro l);
    Livro findByGenre(Livro l);
    Livro findByYear(Livro l);
    Livro findByAuthor(Livro l);
}
