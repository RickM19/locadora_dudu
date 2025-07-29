package br.edu.ufersa.poo.dudu.model.strategy;

import br.edu.ufersa.poo.dudu.model.entities.Produto;

public abstract class ProdutoStrategy {
    public abstract void configurarProduto(
            Produto produtoBase,
            String titulo,
            String autorOuBanda,
            String categoria,
            int qtdExemplares,
            int anoOuZero,
            int paginasOuZero,
            double valorAluguel
    );
}
