package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Disco;

public interface DiscoRepository extends GenericRepository<Disco> {
    Disco findByBandName(Disco d);
    Disco findById(Disco d);
    Disco findByTitle(Disco d);
    Disco findByGenre(Disco d);
}
