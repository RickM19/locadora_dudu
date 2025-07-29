package br.edu.ufersa.poo.dudu.model.factory;

import br.edu.ufersa.poo.dudu.model.entities.Produto;

public abstract  class ProdutoFactory {
    abstract Produto criarProduto(String tipo);
}
