package br.edu.ufersa.poo.dudu.model.factory;

import br.edu.ufersa.poo.dudu.model.entities.Produto;

public abstract  class ProdutoFactory {
    abstract Produto criarProduto(
            String tipo,
            String titulo,
            String autor,
            String categoria,
            int unidades,
            int paginas,
            int ano,
            double valor
    );
}
