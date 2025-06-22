package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Disco;

public interface DiscoService extends GenericService<Disco>{
    Disco buscarPorNomeBanda(String nomeBanda);
    Disco buscarPorId(long id);
    Disco buscarPorTitulo(String titulo);
    Disco buscarPorGenero(String genero);
    void excluir(long id);
    void alterarEstoque(long id, int qtd);
    void alugar(long id);
    void devolver(long id);
}
