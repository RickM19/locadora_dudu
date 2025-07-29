package br.edu.ufersa.poo.dudu.model.strategy;

import br.edu.ufersa.poo.dudu.model.entities.Livro;
import br.edu.ufersa.poo.dudu.model.entities.Produto;

public class LivroStrategy extends ProdutoStrategy {
    @Override
    public void configurarProduto(
            Produto produtoBase,
            String titulo,
            String autor,
            String categoria,
            int qtdExemplares,
            int paginas,
            int ano,
            double valorAluguel
    ) {
        Livro livro = (Livro) produtoBase;
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setQtdExemplares(qtdExemplares);
        livro.setAnoPublicacao(ano);
        livro.setQtdPaginas(paginas);
        livro.setValorAluguel(valorAluguel);
        livro.setQtdDisponivelAluguel(qtdExemplares);
    }
}
