package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Disco;
import br.edu.ufersa.poo.model.entities.Livro;

public interface DiscoRepository extends GenericRepository<Disco> {
    Disco findByBandName(String bandName);
    Disco findById(long id);
    Disco findByTitle(String title);
    Disco findByGenre(String genre);
}
