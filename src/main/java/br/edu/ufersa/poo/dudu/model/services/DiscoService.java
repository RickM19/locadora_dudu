package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Disco;

public interface DiscoService extends GenericService<Disco>{
    Disco buscarPorNomeBanda(Disco d);
    Disco buscarPorId(Disco d);
    Disco buscarPorTitulo(Disco d);
    Disco buscarPorGenero(Disco d);
    void excluir(Disco d);
    void alterarEstoque(Disco d);
    void alugar(Disco d);
    void devolver(Disco d);
}
