package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Disco;

public interface DiscoRepository extends GenericRepository<Disco> {
    Disco findByBandName(String bandName);
    Disco findById(long id);
    Disco findByTitle(String title);
    Disco findByGenre(String genre);
}
