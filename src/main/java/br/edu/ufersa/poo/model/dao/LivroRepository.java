package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Livro;


import java.util.List;

public interface LivroRepository {
    Livro findById(long id);
    List<Livro> findAll();
    void save(Livro l);
    void update(Livro l);
    void delete(Livro l);
    Livro findByTitle(String title);
    Livro findByGenre(String genre);
    Livro findByYear(int year);
    Livro findByAuthor(String Author);
}
