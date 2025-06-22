package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Livro;


import java.util.List;

public interface LivroRepository extends GenericRepository<Livro> {
    Livro findById(long id);
    Livro findByTitle(String title);
    Livro findByGenre(String genre);
    Livro findByYear(int year);
    Livro findByAuthor(String Author);
}
