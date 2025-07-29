package br.edu.ufersa.poo.dudu.model.factory;

import br.edu.ufersa.poo.dudu.model.entities.Disco;
import br.edu.ufersa.poo.dudu.model.entities.Livro;
import br.edu.ufersa.poo.dudu.model.entities.Produto;
import br.edu.ufersa.poo.dudu.model.strategy.DiscoStrategy;
import br.edu.ufersa.poo.dudu.model.strategy.LivroStrategy;
import br.edu.ufersa.poo.dudu.model.strategy.ProdutoStrategy;

public class ConcreteProdutoFactory extends ProdutoFactory {
    private ProdutoStrategy strategy;
    public Produto criarProduto(
            String tipo,
            String titulo,
            String autor,
            String categoria,
            int unidades,
            int paginas,
            int ano,
            double valor
    ) {

        Produto produto;
        switch (tipo.toUpperCase()) {
            case "LIVRO":
                setStrategy(new LivroStrategy());
                produto = new Livro();
                break;
            case "DISCO":
                setStrategy(new DiscoStrategy());
                produto = new Disco();
                break;
            default:
                throw new IllegalArgumentException("Tipo de produto inválido");
        }

        strategy.configurarProduto(produto, titulo, autor, categoria, unidades, paginas, ano, valor);
        return produto;
    }

    public void setStrategy(ProdutoStrategy strategy) {
        if(strategy != null) {
            this.strategy = strategy;
        }
    }
}
