package br.edu.ufersa.poo.dudu.model.strategy;

import br.edu.ufersa.poo.dudu.model.entities.Disco;
import br.edu.ufersa.poo.dudu.model.entities.Produto;

public class DiscoStrategy extends ProdutoStrategy {
    @Override
    public void configurarProduto(
            Produto produtoBase,
            String titulo,
            String banda,
            String categoria,
            int qtdExemplares,
            int zero,
            int zero2,
            double valorAluguel
    ) {
        Disco disco = (Disco) produtoBase;
        disco.setTitulo(titulo);
        disco.setNomeBanda(banda);
        disco.setCategoria(categoria);
        disco.setQtdExemplares(qtdExemplares);
        disco.setValorAluguel(valorAluguel);
        disco.setQtdDisponivelAluguel(qtdExemplares);

    }
}
